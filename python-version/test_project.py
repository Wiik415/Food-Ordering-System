import pytest 
from FoodOrderingSystem import WiikShop
from FoodOrderingSystem import display_Menu_Line

def test_Menu_Line():
    assert display_Menu_Line(20, 1) == None
    assert display_Menu_Line(5, 2) == "-----\n"
    assert display_Menu_Line(7, 2) == "-------\n"

def test_Order_calc():
    shop = WiikShop()

    assert shop.order_calc(1,1,1,1) == 72
    assert shop.order_calc(2,4,6,8) == 388
    assert shop.order_calc(0,0,0,0) == 0


def test_init():
    shop = WiikShop()

    assert 1 in shop.menu
    assert 2 in shop.menu
    assert 3 in shop.menu
    assert 4 in shop.menu

    assert shop.menu[1]["price"] == 15.00
    assert shop.menu[2]["price"] == 12.00
    assert shop.menu[3]["price"] == 25.00
    assert shop.menu[4]["price"] == 20.00
