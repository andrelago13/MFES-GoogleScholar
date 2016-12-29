package googlescholar.model;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.overture.codegen.runtime.VDMSeq;

public class Sort {
	public static VDMSeq sort(VDMSeq s) {
		VDMSeq res = s.copy();
		res.sort(null);
		return res;
	}
}
