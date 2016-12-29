package googlescholar.model;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ScholarUtils {
  public static Number GetHIndex(final VDMSeq vals) {

    VDMMap s = MapUtil.map();
    Number vals_len = vals.size();
    Number min = 0L;
    Number temp = 0L;
    Number sum = 0L;
    long toVar_1 = vals_len.longValue() + 1L;

    for (Long i = 1L; i <= toVar_1; i++) {
      s = MapUtil.munion(Utils.copy(s), MapUtil.map(new Maplet(i, 0L)));
    }
    long toVar_2 = vals_len.longValue();

    for (Long i = 1L; i <= toVar_2; i++) {
      min = Min(vals_len, ((Number) Utils.get(vals, i)));
      temp = ((Number) Utils.get(s, min.longValue() + 1L));
      temp = temp.longValue() + 1L;
      s = MapUtil.override(Utils.copy(s), MapUtil.map(new Maplet(min.longValue() + 1L, temp)));
    }
    vals_len = MapUtil.dom(Utils.copy(s)).size();
    long toVar_3 = vals_len.longValue();

    for (Long i = 1L; i <= toVar_3; i++) {
      temp = vals_len.longValue() - i.longValue() + 1L;
      sum = sum.longValue() + ((Number) Utils.get(s, temp)).longValue();
      if (sum.longValue() >= temp.longValue() - 1L) {
        return temp.longValue() - 1L;
      }
    }
    return 0L;
  }

  public static Number Min(final Number n1, final Number n2) {

    if (n1.longValue() < n2.longValue()) {
      return n1;
    }

    return n2;
  }

  public ScholarUtils() {}

  public String toString() {

    return "ScholarUtils{}";
  }
}
