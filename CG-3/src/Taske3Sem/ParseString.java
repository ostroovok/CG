package Taske3Sem;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseString {
    private String str;

    public ParseString(String str) {
        this.str = str;
    }

    public LinkedList<Integer> parse(){
        LinkedList<Integer> outList = new LinkedList<>();
        if(str.equals("y = x")){
            outList.add(1);
            outList.add(1);
            return outList;
        }else {
            String parseStr;
            LinkedList<String> parseList = new LinkedList<>();
            String[] arrStr = str.split("=");
            parseStr = arrStr[1];
            parseStr = parseStr.replaceAll(" ", "");
            arrStr = parseStr.split("[+-]");
            Pattern p = Pattern.compile("-((\\s)?\\d+)");
            Matcher m = p.matcher(parseStr);
            while (m.find()) {
                parseList.add(m.group().replaceAll("\\s", ""));
            }
            for (int i = 0; i < arrStr.length; i++) {
                String[] temp;
                temp = arrStr[i].split("\\*");
                int k = Integer.parseInt(temp[0]);
                outList.add(k);
                int degree;
                if (temp.length == 1) {
                    degree = 0;
                    outList.add(degree);
                } else {
                    if (temp[1].length() > 1) {
                        degree = Integer.parseInt(temp[1].split("\\^")[1]);
                        outList.add(degree);
                    } else {
                        degree = 1;
                        outList.add(degree);
                    }
                }
                System.out.println("k: " + k + "  " + "degree: " + degree);
            }
            for (int i = 0; i < outList.size(); i += 2) {

                for (String stringValue : parseList) {
                    if (Integer.toString(outList.get(i)).equals(stringValue.replaceAll("\\W", ""))) {
                        if (stringValue.startsWith("-")) {
                            int temp = 0 - outList.get(i);
                            outList.remove(i);
                            outList.add(i, temp);
                        }
                    }
                }
            }
        }
        return outList;
    }
}
