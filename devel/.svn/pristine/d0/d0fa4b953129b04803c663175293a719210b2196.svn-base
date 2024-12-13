function hideShow(thedoc, target, type) {

  obj=(thedoc.all) ? thedoc.all[target] : thedoc.getElementsById(target);

	if (obj==null) return;

  if (isNaN(obj.length)) {

    if (type) obj.style.display='inline';

    else obj.style.display='none';

  } else {

    for (i=0; i<obj.length; i++) {

      if (type) obj[i].style.display='inline';

      else obj[i].style.display='none';

    }

	}

}





function hideShowKategoriPemohonDis(flag)

{ 

    if (flag == '1') 

    {//Individu 

        hideShow(document, 'individu', true)//bukak

        hideShow(document, 'perbadanan', false)

        hideShow(document, 'pertubuhan', false)

    }

    else if(flag == '2')

    {

        hideShow(document, 'individu', false)

        hideShow(document, 'perbadanan', true)//bukak

        hideShow(document, 'pertubuhan', false)

    }

    else if(flag == '3') 

    {

        hideShow(document, 'individu', false)

        hideShow(document, 'perbadanan', false)

        hideShow(document, 'pertubuhan', true)//bukak

    }

}



function hideShowTarafKahwin(flag)

{ 



    if(flag == '2')//2 = dalam table kod_tarafPerkahwinan = 2- berkahwin  

        hideShow(document, 'kahwin', true)//bukak

    else

        hideShow(document, 'kahwin', false)



}



