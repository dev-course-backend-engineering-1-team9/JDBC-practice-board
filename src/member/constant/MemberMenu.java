package member.constant;

public enum MemberMenu {

    JOIN("1"),
    LOGIN("2"),
    EXIT("-1"),
    NONE("0");

    private final String selectNum;
    MemberMenu(String selectNum) {
        this.selectNum = selectNum;
    }

    public String getSelectNum() {
        return selectNum;
    }

    public static MemberMenu fromString(String selectNum) {
        for (MemberMenu menu : MemberMenu.values()) {
            if (menu.getSelectNum().equals(selectNum)) {
                return menu;
            }
        }
        return NONE;
    }
}
