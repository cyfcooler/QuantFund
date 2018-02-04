source ./utils.sh
for i in `cat ./candidate.txt`
do
echo "Delete "$i
operation d $i
done
