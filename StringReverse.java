public class StringReverse {

    public static String reverseIter(String str){
        String strReversed = "";

        for(int i  = 0; i < str.length(); i++){
            strReversed = str.substring(i,i+1) + strReversed;
        }

        return strReversed;

    }

    public static String reverse(String str){
        // all Recursive methods have a terminating case
        if(str.equals("")){
            return str;
        }
        //must make problem simpler
        String firstChar = str.substring(0,1);
        String restOfString = str.substring(1);

        //recurse - calling this method with a simpler problem
        String restOfStringReversed = reverse(restOfString);

        String strReversed = restOfStringReversed + firstChar;
        return strReversed;
    }
}
