from bs4 import BeautifulSoup as bS
import sys


from urllib import request
url = sys.argv[1]
html = request.urlopen(url).read().decode('utf8')


soup = bS(html, 'html.parser')
title = soup.find('title')
    
print(title.string)
    
