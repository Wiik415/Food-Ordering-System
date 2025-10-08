import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;

public class FoodOrderProject {
    public static void main(String args[]) {
        OrderMainFunc();
    }

    private static void OrderMainFunc() {
        String showTot = "";
        String UserInp;
        int userin = 0; int usercount = 0;
        int order_1 = 0; int order_2 = 0; int order_3 = 0;
        int order_4 = 0; int order_5 = 0; int order_6 = 0;
        int order_7 = 0; int order_8 = 0; int ContinueOrder = 0;
        double total_price = 0;
        MenuFrame();
        do {

            try {
                UserInp = JOptionPane.showInputDialog(null, "Enter your food option.");
                if (UserInp == null) System.exit(0);
                userin = Integer.parseInt(UserInp);

                if(userin <1 || userin >8){
                    JOptionPane.showMessageDialog(null, "Invalid Option, try again",
                     "Invalid Menu Option", 0);
                     continue;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid type of input." +
                        "\nPlease enter your option again.", "Invalid Option", 0);
                        continue;
            }

            if (1 <= userin && userin <= 8) {
                while (true) {
                    try{
                        UserInp = JOptionPane.showInputDialog("Enter Quantity" +
                                "\nNotice: Quantity Limited to 10 sets only.");
                        if (UserInp == null)
                            System.exit(0);
                        usercount = Integer.parseInt(UserInp);
                    }catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid type of input." +
                            "\nPlease enter your option again.", "Invalid Option", 0);
                            continue;
                    }

                    if (1 <= usercount && usercount <= 10) {
                        switch (userin) {
                            case 1 -> order_1 += usercount;
                            case 2 -> order_2 += usercount;
                            case 3 -> order_3 += usercount;
                            case 4 -> order_4 += usercount;
                            case 5 -> order_5 += usercount;
                            case 6 -> order_6 += usercount;
                            case 7 -> order_7 += usercount;
                            case 8 -> order_8 += usercount;
                            default -> {
                                JOptionPane.showMessageDialog(null, "Invalid order, end program.",
                                 "Invalid Order Type",0);
                                System.exit(0);
                            } // default in switch loop
                        } // switch loop, order count
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Not in range (Qty: 1-10), try again.",
                                "Invalid Range Of Quantity", 0);
                    }
                } //while loop 
            }// if else, food menu in the range

                ContinueOrder = JOptionPane.showOptionDialog(null, "Continue Order?",
             "Continue Order Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, 3,
              null, null, 0);

              if(ContinueOrder == JOptionPane.YES_OPTION){
                continue;
              }else if(ContinueOrder == JOptionPane.NO_OPTION){
                break;
              }else{
                System.exit(0);
              }

        } while (true);
        
        total_price = CalcTot(order_1, order_2, order_3, order_4, order_5, order_6, order_7, order_8);
        showTot += String.format("Total Price: RM %.2f" , total_price);
        JOptionPane.showMessageDialog(null, showTot , "Total Price", 1);

        if(order_6 != 0){
            JOptionPane.showMessageDialog(null, "Special Promotion: Free 1 coffee"
        , "Special Promotion", 1);
        }else if(order_7 != 0){
            JOptionPane.showMessageDialog(null, "Special Promotion: Free 1 soft drink"
        , "Special Promotion", 1);
        }else if(order_8 != 0){
            JOptionPane.showMessageDialog(null, "Special Promotion: Free 1 coffee and 1 soft drink"
        , "Special Promotion", 1);
        }

        if (Receipt(total_price, order_1, order_2, order_3,
         order_4, order_5, order_6, order_7, order_8) == true){
         
        JOptionPane.showMessageDialog(null, "Receipt has printed successfully.",
             "Receipt Print", 1);
             System.exit(0);
         }
         System.exit(0);
    }

    private static boolean Receipt(double wo, int a, int b, int c, int d, int e, int f, int g, int h) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formatedDate = currentDateTime.format(formatDate);
        String formatedTime = currentDateTime.format(formatTime);
        String formatedTot = String.format("\n%-39s RM %-10.2f \n", "Total", wo);
        String ItemList = String.format("%-39s  %-10s \n\n", "Item", "Quantity");
        if (a != 0)
            ItemList += String.format("%-39s | %-10d \n\n", "Coffee", a);
        if (b != 0)
            ItemList += String.format("%-39s | %-10d \n\n", "Soft Drink", b);
        if (c != 0)
            ItemList += String.format("%-39s | %-10d \n\n", "Dessert", c);
        if (d != 0)
            ItemList += String.format("%-39s | %-10d \n\n", "Starter", d);
        if (e != 0)
            ItemList += String.format("%-39s | %-10d \n\n", "Main Course", e);
        if (f != 0) {
            ItemList += String.format("%-39s | %-10d \n", "Main Course + Dessert", f);
            ItemList += String.format("%-39s\n\n", "Free of charge: 1 Coffee");
        }
        if (g != 0) {
            ItemList += String.format("%-39s | %-10d \n", "Main Course + Starter", g);
            ItemList += String.format("%-39s\n\n", "Free of charge: 1 Soft Drink");
        }
        if (h != 0) {
            ItemList += String.format("%-39s | %-10d \n", "Combo (Main Course + Starter + Dessert)", h);
            ItemList += String.format("%-39s\n\n", "Free of charge: 1 Coffee and 1 Soft Drink");
        }

        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write("\t\t     Receipt\n");
            writer.write(PrintLineA(50));
            writer.write("\nDate: " + formatedDate + "\t\t    Time: " + formatedTime + "\n");
            writer.write("-" + PrintLineA(48) + "-\n");
            writer.write(ItemList);
            writer.write("+" + PrintLineA(48) + "+\n");
            writer.write(formatedTot);
            writer.write(PrintLineA(50));
            
        } catch (IOException oo) {
            System.out.println("File I/O Error.");
        }
        return true;

    }   

    private static double CalcTot(int a, int b, int c, int d, int e, int f, int g, int h) {
        double total_price;
        total_price = (a * 1.80) + (b * 2.00) + (c * 3.50) + (d * 4.00) + (e * 8.00);
        total_price += (f * 11.00) + (g * 11.50) + (h * 15.00);
        return total_price;
    }

    private static void MenuFrame() {
        JPanel panelIcon = getPanelIcon();
        JPanel panelItem = getPanelItem();
        JFrame frame = new JFrame("Lite Lunch Lounge");
        frame.getContentPane().setBackground(new Color(0xC2BFC4));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // When click close button exit windows
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setLocation(frame.getX(), 0);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(panelIcon);
        frame.add(panelItem);

    }

    private static JPanel getPanelIcon() {
        JLabel Icon = IconSetup();
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(0x222222));
        panelMenu.setBounds(0, 0, 800, 60);
        panelMenu.add(Icon);
        return panelMenu;
    }

    private static JPanel getPanelItem() {
        JTextArea FoodDetail = FoodDetail();
        JPanel item = new JPanel();

        item.setLayout(new BorderLayout());
        item.setBackground(new Color(0x222222));
        item.setBounds(0, 60, 800, 440);
        item.add(FoodDetail);

        return item;
    }

    private static JTextArea FoodDetail() {
        JTextArea FoodDetail = new JTextArea();
        FoodDetail.setForeground(Color.white);
        FoodDetail.setBackground(new Color(0x222222));
        FoodDetail.setLineWrap(true);
        FoodDetail.setEditable(false);
        FoodDetail.setWrapStyleWord(true);
        FoodDetail.setFont(new Font("Arial", Font.PLAIN, 20));
        FoodDetail.setText(PrintMenu());
        return FoodDetail;
    }

    private static JLabel IconSetup() {
        JLabel labelTitle = new JLabel();
        labelTitle.setVisible(true);
        labelTitle.setText("Lite Lunch Lounge");
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        labelTitle.setVerticalAlignment(JLabel.TOP);
        labelTitle.setFont(new Font("Palace Script MT", Font.BOLD, 60));
        labelTitle.setForeground(new Color(0xFFFFFF));
        return labelTitle;
    }

    private static String PrintTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm");
        String formatedTime = currentDateTime.format(formatTime);
        return formatedTime;
    }

    private static String PrintDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatedDate = currentDateTime.format(formatDate);
        return formatedDate;
    }

    private static String PrintLineA(int a) {
        return "-".repeat(a);
    }

    private static String PrintLineB(int a) {
        return "+".repeat(a);
    }

    private static String PrintMenu() {
        String Menu = "";
        Menu += String.format("\nDate: %s Time: %s", PrintDate(), PrintTime());
        Menu += String.format("\n%s%s%s\n", PrintLineB(1), PrintLineA(119), PrintLineB(1));
        Menu += String.format("%-110s  %-10s \n\n", "Item", "Price (RM)");
        Menu += String.format("1.%-110s %-10.2f \n", "Coffee", 1.80);
        Menu += String.format("2.%-109s %-10.2f \n", "Soft Drink", 2.00);
        Menu += String.format("3.%-109s %-10.2f \n", "Dessert", 3.50);
        Menu += String.format("4.%-111s %-10.2f \n", "Starter", 4.00);
        Menu += String.format("5.%-105s %-10.2f \n", "Main Course", 8.00);
        Menu += String.format("6.%-98s %-10.2f \n", "Main Course + Dessert", 11.00);
        Menu += String.format("7.%-99s %-10.2f \n", "Main Course + Starter", 11.50);
        Menu += String.format("8.%-86s %-10.2f \n", "Combo (Main Course + Starter + Dessert)", 15.00);
        Menu += String.format("%s%s%s\n", PrintLineB(1), PrintLineA(119), PrintLineB(1));

        return Menu;
    }
}
