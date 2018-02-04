source ./utils.sh
for i in `cat candidate.txt`
do
echo "Add "$i
operation a $i
done
