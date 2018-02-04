import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * Created by yifeirock on 6/7/17.
 */
public class SelectedProcessor {
    public static void main(String[] args) throws Exception {
        Utils.highlightCells(args, IndexedColors.YELLOW);
    }
}
