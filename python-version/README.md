# Food Ordering System
### Video Demo:  <https://www.youtube.com/watch?v=-9CLNOjs8Zg&t=2s>
### Description:
The project's idea come from the fast food automated ordering system, nowadays consumer don't even need to queue up at the counter just to ordering food, instead they are able to order the food either thru the phone application or the machine at the fast food restaurant. This program simulates the basic food ordering system, which including __Main Menu__, __Food List__, __Order Calculation__ and __Receipt__. Before starting order the program will lead the customers to the main menu part, showing the "WIIK SHOP" logo and current date and time, customers are given options to start ordering food (Button 1) or they can just exit the program by simply press the button 2.

```python
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
```
Afterwards, the program will show 4 sets of food, each set of food including the price listed which looks more like an real ordering system:

* __Hamburger Set__ ----- RM 15.00
* __Corndog Set__ -------- RM 12.00
* __Fried Chicken Set__ -- RM 25.00
* __Ramen Set__ ---------- RM 20.00

The program will ask the customer on how much the food set they wanted. But the quantity is limited to __10 sets each order__, and the quantity __should NOT be less than 0__ otherwise the program will exit immediately, then customer can decide whether to continue the order or go to the receipt part, right after input the quantity:
s
```
Example:

Quantity (Limit 10 sets): 1
Continue?(Y/N): N

If the quantity input not in range of 0 <= food => 10:

Quantity (Limit 10 sets): -1
Invalid range, try again
```

Lastly, the program will calculate the total price of the foods, print the receipt from both sides, which is at the __TERMINAL__ and the __Receipt.txt__, TERMINAL part will only shows the total price, while the Receipt.txt shows the name of the food set, quantity, date time and the logo.

### Error Handling and Input Validation:
Sometimes customer might give an wrong input which cause the system crashes easily, thus to avoid this kind of situation, this program involves exception, so the system will detect the value error if customer accidentally gave the wrong input. For instance, the __Menu()__ part:

```python
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
```
When the program receives the invalid input, it will detected as __ValueError__ and ask the customer to give an correct input

### Print the receipt with using File I/O:
After choosing the food set given from the menu, customer will receive an receipt with a '.txt' file:

```python
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
```

Which will show the "WIIK SHOP" logo, the detail of the order, the price (each food set), and the total price. At the meantime it will print out the date time which is located at top left of the receipt.

