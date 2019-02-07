=======================================================
create checker of arguments command and hide it
*******************************************************
!cc create q {if;{indexof;{get;_rsLevels};{args;0}};>;-1;
  {execcc;qjoin;{args;0}};
  {execcc;adm;Queue {args;0} is not exist{newline}}
}
*******************************************************
!cc hide q
*******************************************************
=======================================================
create alias for the join to the rs8 queue command
*******************************************************
!cc create 8 {execcc;q;rs8}
*******************************************************
!cc sethelp 8 add you to the rs8 queue
*******************************************************
=======================================================
create alias for the join to the rs9 queue command
*******************************************************
!cc create 9 {execcc;q;rs9}
*******************************************************
!cc sethelp 9 add you to the rs9 queue
*******************************************************
=======================================================
create alias for the join to the rs10 queue command
*******************************************************
!cc create 10 {execcc;q;rs10}
*******************************************************
!cc sethelp 10 add you to the rs10 queue
*******************************************************
=======================================================
create alias for the list of the rs8 queue command
*******************************************************
!cc create l8 {execcc;qlist;rs8}
*******************************************************
!cc sethelp l8 show the rs8 queue
*******************************************************
=======================================================
create alias for the list of the rs9 queue command
*******************************************************
!cc create l9 {execcc;qlist;rs9}
*******************************************************
!cc sethelp l9 show the rs9 queue
*******************************************************
=======================================================
create alias for the list of the rs10 queue command
*******************************************************
!cc create l10 {execcc;qlist;rs10}
*******************************************************
!cc sethelp l10 show the rs10 queue
*******************************************************
=======================================================
create alias for the remove from the rs8 queue command
*******************************************************
!cc create -8 {execcc;qleave;rs8}
*******************************************************
!cc sethelp -8 remove user from the rs8 queue. You can specify user to remove in the first argument: `!-8 username`
*******************************************************
=======================================================
create alias for the remove from the rs9 queue command
*******************************************************
!cc create -9 {execcc;qleave;rs9}
*******************************************************
!cc sethelp -9 remove user from the rs9 queue. You can specify user to remove in the first argument: `!-9 username`
*******************************************************
=======================================================
create alias for the remove from the rs10 queue command
*******************************************************
!cc create -10 {execcc;qleave;rs10}
*******************************************************
!cc sethelp -10 remove user from the rs10 queue. You can specify user to remove in the first argument: `!-10 username`
*******************************************************
=======================================================