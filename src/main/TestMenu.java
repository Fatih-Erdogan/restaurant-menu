package main;


import customer.*; 
import items.*;
import manager.*;
import menu.*;
import frames.*;

public class TestMenu {

	public static void main(String[] args) {
				
		Menu menu = new Menu();
		
		//creating manager objects to adding items to menu and MenuItems
		Manager manager1 = new Manager("ADMIN","H132H");
		Manager manager2 = new Manager("Fatih","4718");
		Manager manager3 = new Manager("Jack","1111");
		
		System.out.println("Manager1 has ID: "+manager1.getId()+" with password: H132H");
		System.out.println("Manager2 has ID: "+manager2.getId()+" with password: 4718");
		System.out.println("Manager3 has ID: "+manager3.getId()+" with password: 1111");
		
		int manager1Id = manager1.getId();
		MenuItem.requestPersmission(manager1Id, "H132H");
		Menu.requestPersmission(manager1Id, "H132H");
		
		MenuItem<Soup> soupItem = new MenuItem<>("Soups");
		MenuItem<Dessert> dessertItem = new MenuItem<>("Desserts");
		MenuItem<MainDish> mainDishItem = new MenuItem<>("Main Dishes");
		MenuItem<Starter> starterItem = new MenuItem<>("Starters");
		MenuItem<Drink> drinkItem = new MenuItem<>("Drinks");
		
		Soup soup1 = new Soup("Mercimek Çorbasý",12,10);
		Soup soup2 = new Soup("Ezogelin Çorbasý",12,10);
		Soup soup3 = new Soup("Tavuk Suyu Çorbasý",15,54);
		Soup soup4 = new Soup("Domates Çorbasý",11,78);
		Soup soup5 = new Soup("Þehriye Çorbasý",10,10);
		
		Dessert dessert1 = new Dessert("Baklava",18,5);
		Dessert dessert2 = new Dessert("Künefe",22,7);
		Dessert dessert3 = new Dessert("Kadayýf",15,41);
		Dessert dessert4 = new Dessert("Saint Sebastian",20,888);
		Dessert dessert5 = new Dessert("Lokma",15,2);
		
		MainDish mainDish1 = new MainDish("Kuzu Kaburga",85,78);
		MainDish mainDish2 = new MainDish("Chateaubriand",150,20);
		MainDish mainDish3 = new MainDish("Adana Dürüm",40,18);
		MainDish mainDish4 = new MainDish("Kuzu Þiþ",58,36);
		MainDish mainDish5 = new MainDish("Beyti",62,40);
		
		Starter starter1 = new Starter("Zeytinyaðlý Sarma",40,10);
		Starter starter2 = new Starter("Yoðurtlu Makarna",40,10);
		Starter starter3 = new Starter("Kýzartma",40,10);
		Starter starter4 = new Starter("Paçanga Böreði",40,4);
		Starter starter5 = new Starter("Börülce",40,78);
		
		Drink drink1 = new Drink("Su",5,500);
		Drink drink2 = new Drink("Kola",5,500);
		Drink drink3 = new Drink("Ice Tea",5,500);
		Drink drink4 = new Drink("Sprite",5,500);
		Drink drink5 = new Drink("Fanta",5,500);
		
		
		
		
		soupItem.addItem(soup1);
		soupItem.addItem(soup2);
		soupItem.addItem(soup3);
		soupItem.addItem(soup4);
		soupItem.addItem(soup5);
		
		dessertItem.addItem(dessert1);
		dessertItem.addItem(dessert2);
		dessertItem.addItem(dessert3);
		dessertItem.addItem(dessert4);
		dessertItem.addItem(dessert5);
		
		mainDishItem.addItem(mainDish1);
		mainDishItem.addItem(mainDish2);
		mainDishItem.addItem(mainDish3);
		mainDishItem.addItem(mainDish4);
		mainDishItem.addItem(mainDish5);
		
		starterItem.addItem(starter1);
		starterItem.addItem(starter2);
		starterItem.addItem(starter3);
		starterItem.addItem(starter4);
		starterItem.addItem(starter5);
		
		drinkItem.addItem(drink1);
		drinkItem.addItem(drink2);
		drinkItem.addItem(drink3);
		drinkItem.addItem(drink4);
		drinkItem.addItem(drink5);
		
		
		soupItem.setImagePath("images/soups.jpg");
		dessertItem.setImagePath("images/desserts.jpg");
		mainDishItem.setImagePath("images/mainDishes.jpg");
		starterItem.setImagePath("images/starters.jpeg");
		drinkItem.setImagePath("images/drinks.jpg");
		
		
		
		MenuItem.endManagerSession();
		Menu.endManagerSession();
		
		
		Menu.requestPersmission(manager2.getId(), "4718");
		
		menu.addMenuItem(soupItem);
		menu.addMenuItem(dessertItem);
		menu.addMenuItem(mainDishItem);
		menu.addMenuItem(starterItem);
		menu.addMenuItem(drinkItem);
		
		Menu.endManagerSession();
		
		
		Table table1 = new Table(2);
		Table table2 = new Table(2);
		Table table3 = new Table(4);
		Table table4 = new Table(4);
		Table table5 = new Table(4);
		Table table6 = new Table(4);
		Table table7 = new Table(6);
		Table table8 = new Table(6);
		Table table9 = new Table(8);
		Table table10 = new Table(8);
		
		Customer customer1 = new Customer("Ali",18,5);
		Session session1 = new Session(customer1);
		
		//history is also accessible from GUI
		System.out.println("\n\n");
		System.out.println("MODIFICATION HISTORY:\n");
		
		System.out.println(menu.getModificationHistory());
		System.out.println(MenuItem.getModificationHistory());
		
		
		
		//initializes the GUI
		new MainMenuFrame();
		
		
		
	}

}
