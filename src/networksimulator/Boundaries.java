package networksimulator;

public class Boundaries {
    final int RECT_W = 172; // width of layer rectangles
    final int RECT_H = 66; // height of layer rectangles

    // envelope path
    final int INITIAL_X = 30;
    final int INITIAL_Y = 37; // also TOP_Y
    final int LEFT_X = 171;
    final int BTM_Y = 546;
    final int RIGHT_X = 778;
    final int FINAL_X = 916;

    // x coordinates for each layer
    final int H1APP_X, H1PRES_X, H1SES_X, H1TRANS_X, H1NW_X, H1DL_X, H1PHYS_X;
    final int H2APP_X, H2PRES_X, H2SES_X, H2TRANS_X, H2NW_X, H2DL_X, H2PHYS_X;

    // y coordinates for each layer
    final int TAPP_Y, TPRES_Y, TSES_Y, TTRANS_Y, TNW_Y, TDL_Y, TPHYS_Y;
    final int BAPP_Y, BPRES_Y, BSES_Y, BTRANS_Y, BNW_Y, BDL_Y, BPHYS_Y;

    public Boundaries() {
        H1APP_X = H1PRES_X = H1SES_X = H1TRANS_X = H1NW_X = H1DL_X = H1PHYS_X = 112;
        H2APP_X = H2PRES_X = H2SES_X = H2TRANS_X = H2NW_X = H2DL_X = H2PHYS_X = 719;

        // y coordinates for top of layer
        TAPP_Y = 22;
        TPRES_Y = 95;
        TSES_Y = 167;
        TTRANS_Y = 240;
        TNW_Y = 312;
        TDL_Y = 385;
        TPHYS_Y = 457;

        // y coordinates for bottom of layer
        BAPP_Y = 88;
        BPRES_Y = 161;
        BSES_Y = 233;
        BTRANS_Y = 306;
        BNW_Y = 378;
        BDL_Y = 451;
        BPHYS_Y = 523;
    }
}