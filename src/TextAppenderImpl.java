import java.util.Arrays;
import java.util.MissingResourceException;

/**
 * Created by LunaFlores on 12/5/16.
 */
public class TextAppenderImpl implements TextAppender {
    private TextContainer container[] = new TextContainer[10];

    @Override
    public void open(String key) throws AlreadyExistsException {
        TextContainer textContainer = new TextContainer(key);

        for (int i = 0; i < container.length; ++i) {
            if (container[i] != null) {
                if (container[i].getKey().equals(key)) {
                    throw new AlreadyExistsException("this key already exists");
                }
            }
        }

        for (int i = 0; i < container.length; ++i) {
            if (container[i] == null) {
                TextContainer textContainer1 = new TextContainer(key);
                container[i] = textContainer1;
                break;
            }
        }
    }

    @Override
    public void append(String key, String text) throws DoesNotExistException, CannotAppendException {
        TextContainer textContainer = find(key);
        textContainer.append(text);
    }

    @Override
    public void appendEmoji(String key, int emojiIndex) throws DoesNotExistException, CannotAppendException {
        TextContainer textContainer = find(key);
        //call emoji calls with int to get string
        //textContainer.append(text);
        Emojis emojis = new Emojis();
        try {
            String emojiText = emojis.getEmoji(emojiIndex);
            textContainer.append(emojiText);
        } catch (MissingResourceException e){
            throw new CannotAppendException("Bad emoji", e);
        }

    }

    @Override
    public void flush(String key) throws DoesNotExistException {
        TextContainer textContainer = find(key);
        textContainer.setFlushed(true);
    }

    @Override
    public void close(String key) throws DoesNotExistException, NeedsFlushException {
        TextContainer textContainer = find(key);
        if (!textContainer.isFlushed()) {
            throw new NeedsFlushException("Is not flushed");
        }
    }

    private TextContainer find(String key) throws DoesNotExistException {
        TextContainer textContainer = null;
        for (int i = 0; i < container.length; ++i) {
            if (container[i] != null && container[i].getKey().equals(key)) {
                textContainer = container[1];
                break;
            }
        }
        if (textContainer == null) {
            throw new DoesNotExistException("Key does not exist");
        }
        return textContainer;
    }

    @Override
    public String toString() {
        return "TextAppenderImpl{" +
                "container=" + Arrays.toString(container) +
                '}';
    }

}
