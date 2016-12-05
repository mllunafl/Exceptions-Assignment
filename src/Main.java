public class Main {

    public static void main(String[] args) {
        TextAppenderImpl textAppender = new TextAppenderImpl();
        System.out.println(textAppender.toString());

        try {
            textAppender.open("one");
            System.out.println("all good");
        } catch (AlreadyExistsException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }

        try {
            textAppender.open("one");
            System.out.println("ERROR");
        } catch (AlreadyExistsException e) {
            System.out.println("I expect an exception");
            System.out.println(e.getMessage());
        }
        try {
            textAppender.open("two");
            System.out.println("all good");
        } catch (AlreadyExistsException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }


        try {
            textAppender.append("two","Hi");
            System.out.println("all good");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (CannotAppendException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        try {
            textAppender.append("two","Hello");
            System.out.println("all good");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (CannotAppendException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        try {
            textAppender.append("three","Hello");
            System.out.println("ERROR");
        } catch (DoesNotExistException e) {
            System.out.println("I should get an exception" + e.getMessage());
        } catch (CannotAppendException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        try {
            textAppender.close("one");
            System.out.println("ERROR");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (NeedsFlushException e) {
            System.out.println("expecting this exception" + e.getMessage());
        }

        try {
            textAppender.flush("one");
            System.out.println("Flush work");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        try {
            textAppender.close("one");
            System.out.println("I should be here");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (NeedsFlushException e) {
            System.out.println("ERROR" + e.getMessage());
        }

        try {
            textAppender.appendEmoji("two", 1);
            System.out.println("append Emoji works");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (CannotAppendException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }

        System.out.println(textAppender.toString());

        try {
            textAppender.appendEmoji("two", 345);
            System.out.println("ERROR");
        } catch (DoesNotExistException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        } catch (CannotAppendException e) {
            System.out.println("Expect an error");
            System.out.println("the cause was " + e.getCause());
        }
    }


}
