public class HeadAndTailRecursion
{
    public static void main(String[] args)
    {
        System.out.print("Tail Recursion: ");
        tail("blizzard");
        
        System.out.println("");
        
        System.out.print("Head Recursion: ");
        head("blizzard");        
        
    }

    public static void tail(String str)
    {
        if(str.length() == 0)
            return;

        System.out.print(str.charAt(0));

        tail(str.substring(1));
    }

    public static void head(String str)
    {
        if(str.length() == 0)
            return;
        
        head(str.substring(1));

        System.out.print(str.charAt(0));
    }
}