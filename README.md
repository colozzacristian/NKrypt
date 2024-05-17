# NKrypt

NKrypt simulates a crypto wallet, no real money is involved.

You can test out your investment skills safely using this app!

# How to use it

## Starting
After starting the application you'll be greated by a Welcome screen, prompting you to either login from a file or create a new one. 

This idea was inspired from [KeePassXC](https://keepassxc.org/); indeed all the "save files" are encrypted!

## Create a new Login
![GIF](https://raw.githubusercontent.com/colozzacristian/NCrypt/main/Videos/image00003.gif)
To create a new login, you need to choose:
- ***an unique nickname***
- ***a safe password***

The user interface is simple and intuitive; we want to involve all several different users.

## Login
![GIF](https://raw.githubusercontent.com/colozzacristian/NCrypt/main/Videos/image00001.gif)
You can login into your account by doing this 2 steps:
- ***select your username from the list***
- ***insert the password***

## Main menu
Once the login is complited, you'll be welcomed by a table containing various crypto currencies and some other fundamental information.
You can select anyone of them to enable some actions such as:
- ***buying***
- ***selling***

**We will add more features in future updates.*

On the top right of the screen you'll find your account balance, you can add money at any time if you need to!

## Add Balance
![GIF](https://raw.githubusercontent.com/colozzacristian/NCrypt/main/Videos/image00004.gif)
As previously said, you can add balance into your account by clicking the dedicated button on the top right of the screen.

Once done, you can add balance into your account by typing the desired amount to add and clicking the "execute transaction" botton.

## Buy a Cryptocurrency
![GIF](https://raw.githubusercontent.com/colozzacristian/NCrypt/main/Videos/image00005.gif)
You can buy cryptocurrency whenever you want on NKrypt by just selecting it and click on the "buy selected" button on the bottom right corner of the screen.

Once clicked, a menu appears.
You now have to:
- ***digit the amount (in €) that you want to buy.
you will see the equivalent amount, in Crypto, under the digited amount.***  
- ***review your order and conferm it by clicking the "Execute transaction" button.***


## Sell a Cryptocurrency
![GIF](https://raw.githubusercontent.com/colozzacristian/NCrypt/main/Videos/image00002.gif)
Why you want to sell your cryptocurrency...

But let's not quibble, whenever you want to sell a cryptocurrency on NKrypt, you can do it by just selecting it and click on the "sell selected" button on the bottom right corner of the screen.

Once clicked, a menu appears.
You now have to:
- ***digit the amount (in Crypto) that you want to sell.
you will see the equivalent amount, in €, above the digited amount.***  
- ***review your order and conferm it by clicking the "Execute transaction" button.***

## The future of NKrypt

We will introduce some fresh new features to NKypt, this is just the beginning.

Some future updates will include:
- ***an user interface to see and analyze the grapth of your favourite Cryptocurrency, including some advices and tutorials for beginners.***
- ***An improved and more detailed view of the Wallet.***
- ***Exchange a Cryptocurrency with another one.***
- ***Some quiz and small games for interacting with the users.***
- ***and more to come...*** 

## Final considerations
NKrypt is an innovative Educative application that can introduce people to cryptocurrency and learn without exposing them to the risk of loosing their money. 

Once the log in is complited you'll be welcomed by a table containing various crypto currencies, you can click on the to enable actions such as:
- buying
- selling
we might add more features.


Please keep in mind that all of this was made using free (as in cost) tools.
We were also limited to what, our API provider and other free tools avaiable, lets us do.
We found some major limits in, expecially, the API calls.

**Thanks for reading and enjoy the App.**

# Techincal things

## Sitography
- [Icons](https://fontawesome.com/)
- [Main logo](https://looka.com)
- [Api](https://www.cryptocompare.com/)
## Files meanings

### Code/src/main/java
- Caller: is the thread responsible of doing the api calls.
- CallerCaller: is a thread that serves uniquely as a timer for caller.
- App: is the entrypoint, where all important initializations are done.
- Crypto: is the class describing cryptocurrencies
- CryptoList: is the class containing the list of cryptoes, the balance and the semaphore used to activate Caller.
- FileCrypt: is where al the magic of the files happens. Encryption, decription and despair. 
- StringParserCC: it's an utility tailor made to parse strings.
- LoginController: as the name implies it's the controller for LoginFXML.
- MainUiControlle:as the name implies it's the controller for MainUiFXML.
### Code/resources/com/example
- Style/style.css: is our css file.
- Images: it's where the images are.
- Login.fxml: it's the fxml file for the login menu.
- mainUI.fxml: it's the fxml file for the login menu.
## Tools
- VScode for coding
- Obsidian + excalidraw for the UseCaseDiagram.png
- Intellij Idea for the class diagram
- [HackMD](https://hackmd.io/?nav=overview) to make this README
- git(obviously)
- Scene builder to make the GUI
