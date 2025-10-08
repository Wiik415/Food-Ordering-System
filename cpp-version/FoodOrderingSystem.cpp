#include <iostream>
#include <iomanip>
#include <fstream> // File output
#include <ctime>
using namespace std;


// Declare Global Variables
int item[5];
double meal_price = 0, sales_tax, total_amount;
int currency = 1, tax_applicable, order_num = 1;
const double price[5] = {18,20,15,21,8.50};
const double tax_percentage[3] = {0,2,5};
const string menu_name[5] = {"Mushroom Burger","Crispy Fried Chicken","Fish & Chips","Spaghetti & Meatballs","Hotdog Sandwich"};
const double exch_rate[3] = {1,4.66,5.07};
const string curr_name[3] = {"MYR","USD","EUR"};

// Declare Functions
void order_menu();
void view_order();
void calculate();

// Main Code
int main(){
	int option;
	
	// Loop until '3' is selected
	do{
		cout<<"Food Ordering System"<<"\n\n";
		cout<<"  1  "<<"[Order Meals]"<<endl
			<<"  2  "<<"[View Order]"<<endl
			<<"  3  "<<"[Exit]"<<"\n\n";
		
		cout<<"Enter Option: ";
		cin>>option;
		
		// If option not '1' or '2', terminate
		if(option!=3){
			cout<<"\n\n";
				if(option!=1 && option!=2 && option!=3){
				cout<<"Invalid Option!\n";
				return 1;
				
				// Run function based on option selected
				}
				else{
					switch(option){
						case 1:
							order_menu();
							break;
						case 2:
							view_order();
							break;
					}
				}
		}
	}
	while(option!=3);
	return 0;
}

// Order Meals
void order_menu(){
	// Declare local variables
	char next_meal;
	int meal_code, quantity;
	
	//Looping until next_meal = 'N'
	do{
		
		cout<<"<<Food Menu>>"<<endl
			<<setw(5)<<'1'<<" [Mushroom Burger: RM18]"<<endl
			<<setw(5)<<'2'<<" [Crispy Fried Chicken: RM20]"<<endl
			<<setw(5)<<'3'<<" [Fish & Chips: RM15]"<<endl
			<<setw(5)<<'4'<<" [Spaghetti & Meatballs: RM21]"<<endl
			<<setw(5)<<'5'<<" [Hotdog Sandwich: RM8.50]"<<"\n\n";
			
		// If meal_code is not on the menu, terminate
		cout<<"Enter Meal Code: ";
		cin>>meal_code;
		cout<<endl;
		if(meal_code<=0 || meal_code>5){
			cout<<"Invalid Meal Code!\n";
			exit(1);
		}
		
		// If quantity is less than or equal to 0, terminate
		cout<<"Enter Quantity: ";
		cin>>quantity;
		cout<<endl;
		if(quantity<=0 || quantity>999){
			cout<<"Invalid Quantity!\n";
			exit(1);
		}
		
		//Assigning global variables values entered
		item[meal_code-1] += quantity;
		meal_price += quantity * price[meal_code-1];
		
		// If continue is not 'Y' or 'N', terminate
		cout<<setw(4)<<"Continue next Meal [Y/N]: ";
		cin>>next_meal;
		cout<<endl;
		next_meal = toupper(next_meal);
		if(next_meal!='Y' && next_meal!='N'){
			cout<<"Invalid Option!\n";
			exit(1);
		}
	}
	while(next_meal=='Y');

	// Selecting currency
	cout<<"Select your currency: "<<"1) [Ringgit(RM)]\n"
		<<setw(22)<<""<<"2) [US Dollar(USD)]\n"
		<<setw(22)<<""<<"3) [Euro(EUR)]\n\n";
	cout<<"Enter Currency: ";
	cin>>currency;
	cout<<endl;
	
	// If currency is not an option, terminate
	if(currency<=0 || currency>3){
		cout<<"Invalid Currency!\n";
		exit(1);
	}
}

// View meals
void view_order(){
	// Calculate prices and convert
	calculate();
	
	// Declare output variable
	ofstream receipt;
	
	// Declare local variables
	int item_num = 1;
	char print;
	
	// Declare current date
	time_t current = time(0);
	tm* date = localtime(&current);
	
	// Upper portion of the receipt
	cout<<"Order No: "<<left<<setw(27)<<order_num<<" Date: "<<date->tm_mday<<'/'<<1 + date->tm_mon<<'/'<<1900 + date->tm_year<<endl
		<<setw(5)<<"No"<<setw(20)<<"Meal"<<setw(18)<<"Quantity"<<"Total"<<'('<<curr_name[currency-1]<<')'<<endl
		<<setfill('-')<<setw(55)<<""<<setfill(' ')<<endl;
		
	// Ordered items
	for(int i=0;i<=4;i++){
		if(item[i]!=0){
			cout<<left<<setw(5)<<item_num<<setw(23)<<menu_name[i]<<setw(4)<<item[i]<<setw(15)<<right<<fixed<<setprecision(2)<<(price[i]*item[i])/exch_rate[currency-1]<<endl;
			item_num++;
		}
	}
	
	// Lower portion of the receipt
	cout<<setfill('-')<<setw(55)<<""<<setfill(' ')<<endl;
	cout<<setw(20)<<""<<"Meal Price"<<right<<setw(17)<<fixed<<setprecision(2)<<meal_price<<endl
		<<setw(20)<<""<<"Tax ("<<setprecision(0)<<tax_percentage[tax_applicable]<<"%)"<<setw(19)<<setprecision(2)<<sales_tax<<endl
		<<left<<setw(20)<<""<<"TOTAL"<<right<<setw(22)<<setprecision(2)<<total_amount<<"\n\n";
	
	// Reset item_num
	item_num = 1;
	
	// Ask user if they want to generate receipt
	cout<<"Print?(Y/N): ";
	cin>>print;
	print = toupper(print);
	
	// If option is not 'Y' or 'N', terminate
	if(print!='Y' && print!='N'){
		cout<<"Invalid Option!\n";
		exit(1);
	}
	
	// Generate text file "Receipt.txt" if print = 'Y'
	else if(print=='Y'){
		receipt.open("Receipt.txt");
		receipt<<"Order No: "<<left<<setw(27)<<order_num<<" Date: "<<date->tm_mday<<'/'<<1 + date->tm_mon<<'/'<<1900 + date->tm_year<<endl
		<<setw(5)<<"No"<<setw(20)<<"Meal"<<setw(16)<<"Quantity"<<"Total"<<'('<<curr_name[currency-1]<<')'<<endl
		<<setfill('-')<<setw(55)<<""<<setfill(' ')<<endl;
		for(int i=0;i<=4;i++){
			if(item[i]!=0){
				receipt<<setw(5)<<item_num<<setw(23)<<menu_name[i]<<setw(4)<<item[i]<<setw(15)<<right<<fixed<<setprecision(2)<<(price[i]*item[i])/exch_rate[currency-1]<<left<<endl;
				item_num++;
			}
		}
		receipt<<setfill('-')<<setw(55)<<""<<setfill(' ')<<endl
		<<setw(20)<<""<<"Meal Price"<<right<<setw(17)<<fixed<<setprecision(2)<<meal_price<<endl
		<<setw(20)<<""<<"Tax ("<<setprecision(0)<<tax_percentage[tax_applicable]<<"%)"<<setw(19)<<setprecision(2)<<sales_tax<<endl
		<<left<<setw(20)<<""<<"TOTAL"<<right<<setw(22)<<setprecision(2)<<total_amount<<"\n\n";
		receipt.close();
	}
	
	// Setting variables for next customer
	order_num++;
	meal_price = 0;
	currency = 1;
	for(int n=0;n<=4;n++){
		item[n] = 0;
	}
}

// Calculating price and converting currency
void calculate(){
	// Assigning tax_applicable to tax_percentage array number
	if(meal_price<=15){
		tax_applicable = 0;
	}
	else if(meal_price<=50){
		tax_applicable = 1;
	}
	else if(meal_price>50){
		tax_applicable = 2;
	}
	
	// Calculating price then converting to currency
	sales_tax = (meal_price * (tax_percentage[tax_applicable]/100)) / exch_rate[currency-1];
	meal_price /= exch_rate[currency-1];
	total_amount = meal_price + sales_tax;
}
