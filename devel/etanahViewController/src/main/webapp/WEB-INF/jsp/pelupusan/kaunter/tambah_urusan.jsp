<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
<title>Kaunter: Utama</title>

</head>
<body>

<script language="javascript">
    function updateSelect(idx){
        var textKodUrusanKod = document.getElementById('kodUrusanKod' + idx);
        if (textKodUrusanKod.value.length > 0){
            var selectKodUrusan = document.getElementById('kodUrusan' + idx);
            selectKodUrusan.selectedIndex = 0;
            var kod = textKodUrusanKod.value.toUpperCase();
            for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == kod){
                    selectKodUrusan.selectedIndex = i;
                    updateJabatan(idx, selectKodUrusan.options[i].parentNode.label); 
                    break;
                }  
            }
            if (selectKodUrusan.selectedIndex == 0){
                alert('Kod Urusan ' + kod + ' tidak dijumpai.');
            }
        }

    }
    
    function updateKod(i){
        var selectKodUrusan = document.getElementById('kodUrusan' + i);
        if (selectKodUrusan.selectedIndex > 0){
            var textKodUrusanKod = document.getElementById('kodUrusanKod' + i);
            textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
            updateJabatan(i, selectKodUrusan.options[selectKodUrusan.selectedIndex].parentNode.label); 
        }
    }

    function updateJabatan(whichItem, namaJabatan){
        var selectJabatan = document.getElementById('kodJabatan' + whichItem);
        for (i = 0; i < selectJabatan.length; i++){
            if (selectJabatan.options[i].text == namaJabatan){
                   selectJabatan.selectedIndex = i;
                   break;
            }
        }
    }

    function selectUrusanForJabatan(whichItem){
        var kodJabatan = $("#kodJabatan" + whichItem + " option:selected").text();

        var found = false;
        var selectUrusan = document.getElementById("kodUrusan" + whichItem);
        for (i = 0; i < selectUrusan.length; i++){
            if (selectUrusan.options[i].parentNode.label == kodJabatan){
                selectUrusan.selectedIndex = i;
                found = true;
                break;
            }
        } 

        if (!found) selectUrusan.selectedIndex = 0;
    }

</script>


 <span class=title>Langkah 1: Tambah Urusan Lain</span><br/>

 <span class=instr>Medan bertanda <em>*</em> adalah mandatori.</span><br/><br/>

<stripes:messages />
<stripes:errors />

<!--  PERMOHOHONAN/PERSERAHAN-->

<stripes:form action="/pelupusan/kaunter" >

<fieldset class="aras1">

    <legend>Pendaftaran Urusan</legend>
    
    <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />
    <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
    <c:set scope="request" var="senaraiUrusanPendaftaran" value="${listUtil.senaraiUrusanPelupusanBelakangKaunter}" />

    <p><label for="kodUrusankod"><em>*</em>Urusan 1</label><nobr>
    
        <stripes:select name="senaraiUrusan[0].kodJabatan" id="kodJabatan0" onchange="selectUrusanForJabatan(0)" >
            <option value="0">Pilih Unit...</option>
            <c:forEach items="${senaraiJabatan}" var="j" >
               <option value="${j.kod}" >${j.nama}</option>
            </c:forEach>            
        </stripes:select>
    
        <stripes:text name="senaraiUrusan[0].kodUrusan" id="kodUrusanKod0" onblur="javascript:updateSelect(0);" size="6" />
        
        <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />

        <stripes:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);" 
                style="width:400px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
                <optgroup label="${jabatanNama}" />            
                <c:forEach items="${pilihanKodUrusan}" var="i" >
                    <c:if test="${jabatanNama != i.jabatanNama}" >
                        <c:set var="jabatanNama" value="${i.jabatanNama}" />
                        <optgroup label="${jabatanNama}" />
                    </c:if>
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
        </stripes:select>
    </nobr>
    </p>
    
    <p align="middle">Untuk Urusan Berangkai</p>
    
    <p><label for="kodUrusankod">Urusan 2</label><nobr>

        <stripes:select name="senaraiUrusan[1].kodJabatan" id="kodJabatan1" onchange="selectUrusanForJabatan(1)" >
            <option value="0">Pilih Urusan...</option>
            <option value="16">Pendaftaran</option>
       </stripes:select>

        <stripes:text name="senaraiUrusan[1].kodUrusan" id="kodUrusanKod1" onblur="javascript:updateSelect(1);" size="6" />

        <stripes:select name="senaraiUrusan[1].kodUrusanPilih" id="kodUrusan1" onchange="javascript:updateKod(1);" 
                style="width:400px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
                <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
        </stripes:select>
    </nobr>
    </p>
    
    <p><label for="kodUrusankod">Urusan 3</label><nobr>

        <stripes:select name="senaraiUrusan[2].kodJabatan" id="kodJabatan2" onchange="selectUrusanForJabatan(2)" >
            <option value="0">Pilih Urusan...</option>
            <option value="16">Pendaftaran</option>
       </stripes:select>

        <stripes:text name="senaraiUrusan[2].kodUrusan" id="kodUrusanKod2" onblur="javascript:updateSelect(2);" size="6" />

        <stripes:select name="senaraiUrusan[2].kodUrusanPilih" id="kodUrusan2" onchange="javascript:updateKod(2);" 
                style="width:400px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
                <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
        </stripes:select>
    </nobr>
    </p>
    
    <p><label for="kodUrusankod">Urusan 4</label><nobr>

        <stripes:select name="senaraiUrusan[3].kodJabatan" id="kodJabatan3" onchange="selectUrusanForJabatan(3)" >
            <option value="0">Pilih Urusan...</option>
            <option value="16">Pendaftaran</option>
       </stripes:select>

        <stripes:text name="senaraiUrusan[3].kodUrusan" id="kodUrusanKod3" onblur="javascript:updateSelect(3);" size="6" />

        <stripes:select name="senaraiUrusan[3].kodUrusanPilih" id="kodUrusan3" onchange="javascript:updateKod(3);" 
                style="width:400px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
                <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
        </stripes:select>
    </nobr>
    </p>
    
    <p><label for="kodUrusankod">Urusan 5</label><nobr>

        <stripes:select name="senaraiUrusan[4].kodJabatan" id="kodJabatan4" onchange="selectUrusanForJabatan(4)" >
            <option value="0">Pilih Urusan...</option>
            <option value="16">Pendaftaran</option>
       </stripes:select>

        <stripes:text name="senaraiUrusan[4].kodUrusan" id="kodUrusanKod4" onblur="javascript:updateSelect(4);" size="6" />

        <stripes:select name="senaraiUrusan[4].kodUrusanPilih" id="kodUrusan4" onchange="javascript:updateKod(4);" 
                style="width:400px;" >
            <stripes:option label="Pilih Urusan..."  value="0" />
                <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                </c:forEach>            
        </stripes:select>
    </nobr>
    </p>

    <p>
        <label>&nbsp;</label>
        <stripes:submit name="Step2" value="Seterusnya" class="btn" />
    </p>
    
</fieldset>

<stripes:submit name="updateUrusanJabatan" style="display:none;" />

</stripes:form>

</div>
</body>
</html>