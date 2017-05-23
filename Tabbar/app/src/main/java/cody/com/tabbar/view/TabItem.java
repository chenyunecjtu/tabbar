package cody.com.tabbar.view;

/**
 * Created by apple on 2017/5/23.
 */

public class TabItem {
    private String title;
    private String normalColor;
    private String selectedColor;
    private String normalImg;
    private String selectedImg;

    private Boolean isSelected = false;

    public TabItem(String title, String normalColor, String selectedColor, String normalImg, String selectedImg) {
        this.title = title;
        this.normalColor = normalColor;
        this.selectedColor = selectedColor;
        this.normalImg = normalImg;
        this.selectedImg = selectedImg;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(String normalColor) {
        this.normalColor = normalColor;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public String getNormalImg() {
        return normalImg;
    }

    public void setNormalImg(String normalImg) {
        this.normalImg = normalImg;
    }

    public String getSelectedImg() {
        return selectedImg;
    }

    public void setSelectedImg(String selectedImg) {
        this.selectedImg = selectedImg;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
