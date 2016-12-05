/**
 * Created by LunaFlores on 12/5/16.
 */
public class TextContainer {
    private String key;
    private String text = "";
    private boolean flushed;

    public TextContainer(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getText() {
        return text;
    }

    public boolean isFlushed() {
        return flushed;
    }

    public void setFlushed(boolean flushed) {
        this.flushed = flushed;
    }

    public void append (String appendValue){
        text = text + appendValue;
    }

    public void flush (){
        flushed = true;
    }

    @Override
    public String toString() {
        return "TextContainer{" +
                "key='" + key + '\'' +
                ", text='" + text + '\'' +
                ", flushed=" + flushed +
                '}';
    }
}
