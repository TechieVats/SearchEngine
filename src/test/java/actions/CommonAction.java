package actions;

import java.util.ArrayList;
import java.util.List;

public class CommonAction {
    BingAction bing = new BingAction();
    GoogleAction google = new GoogleAction();

    public List<String> commonResults(List<String> googleList, List<String> bingList) {
        System.out.println("Google List: " + googleList);
        System.out.println("bing List: " + bingList);
        List<String> common = new ArrayList<>();

        for (String g : googleList) {
            for (String b : bingList) {
                if (g.equalsIgnoreCase(b)) {
                    common.add(b);
                }
            }
        }
        System.out.println("common List: " + common);
        return common;
    }

}
