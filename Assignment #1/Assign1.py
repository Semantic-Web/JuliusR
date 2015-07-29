import csv
import collections
from collections import defaultdict
import heapq
import matplotlib.pyplot as plt 
import numpy as np
import textwrap
from tabulate import tabulate



#read csv data file and place only organization and no. of views in list
with open('datagovdatasets.csv') as csvfile:
    reader = csv.DictReader(csvfile)
    source = []
    for row in reader:
        org = row['Organization Name']
        view = row['Views per Month']
        source.append([org, view])
 
def drawChart(x_val, y_val, xticks):
    plt.bar(x_val,y_val,10)
    plt.xticks(x_val,xticks,fontsize=10, rotation='vertical' )
    plt.xlabel('Organization Name')
    plt.ylabel('Views per Month')
    plt.title('Top 10 Visitors')
    plt.grid(True)
    plt.show()
        
#create dictionary to easily aggregate organizations
d = defaultdict(list)
for k, v in source:
        d[k].append(v)

#aggregate organizations and total the views for each org
agg_d = {k:sum(map(int, v)) for k, v in d.items()}


#create a function to find the top ten orgs with the highest number of views (returns list)
def dict_nlargest(d,n):
    
    return heapq.nlargest(n ,d, key = lambda k: d[k])

#identify the top 10 orgs with highest views and return a list
orgs = dict_nlargest(agg_d, 10)


        
#take top ten orgs, find their values, and add it to the 'views' list
views = []
for o in range(0, len(orgs)):
    value = agg_d[orgs[o]]
    views.append(value)

#create and print a table 
table = []
for i in range(0, len(orgs)):
    table.append([orgs[i], views[i]])
print tabulate(table, headers = ["Organization", "Total Views"], tablefmt= "fancy_grid")


#create and print a bar chart
y = views
width = 1
N = len(y)
x = np.arange(N)
labels = [textwrap.fill(text,15) for text in orgs]
fig = plt.figure(num=1, figsize=(50,20))
plt.bar(x, y, width)
plt.xlabel('Total Views')
plt.ylabel('Organization')
plt.xticks(x + width/1.0, labels)
plt.title('Top Ten Organizations')
plt.show()

