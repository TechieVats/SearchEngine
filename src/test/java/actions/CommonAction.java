package actions;

import java.util.ArrayList;
import java.util.List;

public class CommonAction {
    public List<String> commonResults(List<String> googleList, List<String> bingList) {
        List<String> common = new ArrayList<>();
        for (String g : googleList) {
            for (String b : bingList) {
                if (g.equalsIgnoreCase(b)) {
                    common.add(b);
                }
            }
        }
        return common;
    }

}
