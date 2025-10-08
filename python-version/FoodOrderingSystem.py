from datetime import datetime
import sys
from pyfiglet import Figlet
figlet = Figlet()
figlet.setFont(font = "rectangles")
current_datetime = datetime.now()
datetimefor = f"{current_datetime:%Y-%m-%d %H:%M:%S.%f}"[:-4]

class WiikShop:
    def __init__(self):
        self.menu = {
            1: {"name": "Hamburger Set", "price": 15.00},
            2: {"name": "Corndog Set", "price" : 12.00},
            3: {"name": "Fried Chicken Set", "price": 25.00},
            4: {"name": "Ramen Set", "price": 20.00}
        }

    def order_calc(self, q, w, e, r):
        total_price = 0
        order1 = self.menu[1]
        order2 = self.menu[2]
        order3 = self.menu[3]
        order4 = self.menu[4]
        try:
            total_price = (order1["price"] * q) + (order2["price"] * w) + (order3["price"] * e) + (order4["price"] * r)
        except (ValueError, TypeError):
            print("Invalid quantity")
        return total_price

def display_Menu_Line(s,d):
    try:
        d = int(d)
        s = int(s)
        if d == 1:
            for i in range(s):
                print('-', end = '')
            print()

        elif d == 2:
            return ('-' * s) + '\n'
        else:
            raise ValueError

    except ValueError:
        print("Not an integer")
        return ''
    except TypeError:
        print("Invalid Type")
        return ''

def Item_Menu():  # Menu
    print()
    display_Menu_Line(50, 1)
    print("1. Hamburger Set(RM15.00)\n2. Corndog Set(RM12.00)\n" +
        "3. Fried Chicken Set(RM25.00)\n4. Ramen Set(RM20.00)")
    display_Menu_Line(50, 1)
    display_Menu_Line(50, 1)

def Menu():
    print(figlet.renderText("WIIK SHOP") + f"\n {datetimefor}")
    while True:
        display_Menu_Line(50, 1)
        print("1. Order\n2. Exit")
        display_Menu_Line(50, 1)
        try:
            userin = input("Enter: ").strip()

            if userin == '2':
                sys.exit(figlet.renderText("THANK YOU"))
            elif userin == '1':
                return 1
            else:
                raise ValueError
        except ValueError:
            print("Invalid Input\n")
            continue

def Order_count(s, w, x, y, z):
    try:
        food_qty = int(input("Quantity (Limit 10 sets): "))
        if 0 <= food_qty <= 10:
            if s == '1':
                w += food_qty
            elif s == '2':
                    x += food_qty
            elif s == '3':
                    y += food_qty
            elif s == '4':
                    z += food_qty
            else:
                raise ValueError
        else:
            sys.exit("Invalid range, try again.")

        return w, x, y, z

    except ValueError:
        sys.exit("Invalid Input")

def Order_Process():
    set_one = 0
    set_two = 0
    set_three = 0
    set_four = 0
    while True:
        Item_Menu()
        user_order = input("Order: ").strip()
        if user_order in ['1','2','3','4']:
            set_one, set_two, set_three, set_four = Order_count(user_order, set_one,
                                                                 set_two, set_three,set_four)
        else:
            print("Invalid Option")
            continue

        while True: #Inner loop for asking user if they want to continue ordering food
            user_cont = input("Continue?(Y/N): ").strip().upper()
            if user_cont == 'Y':
                break
            elif user_cont == 'N':
                return set_one, set_two, set_three, set_four
            else:
                print("Please enter only Y or N")
                continue

def main():
    wiikshop = WiikShop()
    if Menu() == 1:
        set_one, set_two, set_three, set_four = Order_Process()
        total_price = wiikshop.order_calc(set_one, set_two, set_three, set_four)
        display_Menu_Line(50, 1)
        print(f"Total Price: RM{total_price:.2f}\nReceipt has printed.\n" +
              figlet.renderText("THANK YOU"))

        with open("Receipt.txt",'w') as file:
            file.write("\n\n" + figlet.renderText("WIIK SHOP") + f"DATETIME: {datetimefor}\n"
                         + display_Menu_Line(50, 2))

            if 0 <= set_one and set_one >= 1:
                file.write(f"Hamburger Set * {set_one}\n")
            if 0 <= set_two and set_two >= 1:
                file.write(f"Corndog Set * {set_two}\n")
            if 0 <= set_three and set_three >= 1:
                file.write(f"Fried Chicken Set * {set_three}\n")
            if 0 <= set_four and set_four >= 1:
                file.write(f"Ramen Set * {set_four}\n")
            file.write(display_Menu_Line(50, 2) + f"Total Price: RM{total_price:.2f}\n" + display_Menu_Line(50, 2) +
                        display_Menu_Line(50, 2) + figlet.renderText("THANK YOU"))

if __name__ == "__main__":
    main()
