# Lab - Yarn - MapReduce
<div style="text-align: right"> Mathys Goncalves | Quentin Angelot - M1BDIA </div>

Connect to HADOOP cluster using SSH:

```powershell
[mathys.goncalves@hadoop-edge01 ~]$ kinit
```

First we need to put the csv files :

```ps
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -put trees.csv
```

We simplify the comand using an alias :

```ps
[mathys.goncalves@hadoop-edge01 ~]$ alias mapreduce='yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar'
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce wordcount /user/mathys.goncalves/trees.csv /user/mathys.goncalves/wordcount_out
```

Get the list of the command:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce
  dts: displays the list of distinct containing trees
  height: calculates the height of the tallest tree of each kind
  heightSorted: sort the trees height from smallest to largest
  kinds: calculates the number of trees of each kinds
  most: displays the district that contains the most trees
  oldest: displays the district where the oldest tree is
  species: displays the list of distinct species of trees
  wordcount: A map/reduce program that counts the words in the input files
```

**1.8.1** Displays the list of distinct containing trees:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce dts /user/mathys.goncalves/trees.csv /user/mathys.goncalves/dts_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/dts_out/part-r-00000
  11      1
  12      29
  13      2
  14      3
  15      1
  16      36
  17      1
  18      1
  19      6
  20      3
  3       1
  4       1
  5       2
  6       1
  7       3
  8       5
  9       1
```

**1.8.2** Displays the list of different species trees:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce species /user/mathys.goncalves/trees.csv /user/mathys.goncalves/species_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/species_out/part-r-00000
  araucana        1
  atlantica       2
  australis       1
  baccata 2
  bignonioides    1
  ...
  ulmoides        1
  virginiana      2
  x acerifolia    11
```

**1.8.3** Calculates the number of trees of each kinds:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce kinds /user/mathys.goncalves/trees.csv /user/mathys.goncalves/kinds_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/kinds_out/part-r-00000
  Aesculus        3
  Ailanthus       1
  Alnus   1
  ...
  Ulmus   1
  Zelkova 4
```

**1.8.4** Calculates the height of the tallest tree of each kind:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce height /user/mathys.goncalves/trees.csv /user/mathys.goncalves/height_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/height_out/part-r-00000
  Acer    16
  Aesculus        30
  Ailanthus       35
  ...
  Ulmus   15
  Zelkova 30
```

**1.8.5** Sort the trees height from smallest to largest:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce heightSorted /user/mathys.goncalves/trees.csv /user/mathys.goncalves/heightsort_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/heightsort_out/part-r-00000

```

**1.8.6** Displays the district where the oldest tree is:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce oldest /user/mathys.goncalves/trees.csv /user/mathys.goncalves/oldest_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/oldest_out/part-r-00000

5       420
```

**1.8.7** Displays the district that contains the most trees:

```ps
[mathys.goncalves@hadoop-edge01 ~]$ mapreduce most /user/mathys.goncalves/trees.csv /user/mathys.goncalves/most_out
[mathys.goncalves@hadoop-edge01 ~]$ hdfs dfs -cat /user/mathys.goncalves/most_out/part-r-00000

16      36
```