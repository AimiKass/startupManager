from bs4 import BeautifulSoup 
import sys
import requests


response = requests.get(sys.argv[1])


soup = BeautifulSoup(response.text,"lxml")
titleTag = soup.html.head.title
    
print (titleTag.string)
    
    
