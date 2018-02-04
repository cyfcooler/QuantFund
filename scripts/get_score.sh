echo "" > score.txt
for i in `cat all.txt`
do
score=`curl http://fund.eastmoney.com/f10/tsdata_$i.html | grep "var avg =" | awk -F'"' '{print $2}'`
echo "$i=$score" | tee -a score.txt
done
