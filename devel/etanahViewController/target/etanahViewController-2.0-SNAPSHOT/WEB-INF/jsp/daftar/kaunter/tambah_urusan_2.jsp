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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
</head>
<body>

<script language="javascript">
    $(document).ready(function() {
         $('form').submit(function() {
                    doBlockUI();
         });
    });
     function doBlockUI() {
                $.blockUI({
                    message: $('#displayBox'),
                    css: {
                        top: ($(window).height() - 50) / 2 + 'px',
                        left: ($(window).width() - 50) / 2 + 'px',
                        width: '50px'
                    }
                });
            }
    
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
                // update kod urusan
                var selectKodUrusan = document.getElementById('kodUrusan' + whichItem);
                var textKodUrusanKod = document.getElementById('kodUrusanKod' + whichItem);
                textKodUrusanKod.value = ''; // nullify first
                if (selectKodUrusan.selectedIndex > 0){
                    textKodUrusanKod.value = selectKodUrusan.options[selectKodUrusan.selectedIndex].value;
                }
                
                break;
            }
        }

        if (!found) selectUrusan.selectedIndex = 0;
    }

</script>

<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>
<span class=title>Langkah 1: Pilih Urusan</span><br/>

<span class=instr>Medan bertanda <em>*</em> adalah mandatori.</span><br/><br/>

<stripes:messages />
<stripes:errors />

<!--  PERMOHOHONAN/PERSERAHAN-->

<stripes:form action="/daftar/kaunter/kesinambungan" >

<stripes:hidden name="selectedItem" />


	    <fieldset class="aras1">
	
	        <legend>Pendaftaran Urusan</legend>
	
	        <c:set scope="request" var="pilihanKodUrusan" value="${actionBean.pilihanKodUrusan}" />
	        <c:set scope="request" var="senaraiJabatan" value="${listUtil.senaraiKodJabatan}" />
	        <c:set scope="request" var="senaraiUrusanPendaftaran" value="${listUtil.senaraiUrusanPendaftaranBelakangKaunter}" />
	
	        <p><label for="kodUrusankod" class="labelspoc"><em>*</em>Urusan</label><nobr>
	
	            <%--<stripes:select name="urusanPermohonan.kodJabatan" id="kodJabatan0" onchange="selectUrusanForJabatan(0)" >
	                <option value="0">Pilih Unit...</option>
	                <c:forEach items="${senaraiJabatan}" var="j" >
	                    <option value="${j.kod}" >${j.nama}</option>
	                </c:forEach>
	            </stripes:select>--%>
	
	            <stripes:text name="senaraiUrusan[0].kodUrusan" id="kodUrusanKod0" onblur="javascript:updateSelect(0);" onkeyup="this.value=this.value.toUpperCase();" size="6" />
	
	            <c:set scope="request" var="jabatanNama" value="${pilihanKodUrusan[0].jabatanNama}" />
	
	            <stripes:select name="senaraiUrusan[0].kodUrusanPilih" id="kodUrusan0" onchange="javascript:updateKod(0);"
	                             >
	                <%--<stripes:option label="Pilih Urusan..."  value="0" />
	                <optgroup label="${jabatanNama}" />
	                <c:forEach items="${pilihanKodUrusan}" var="i" >
	                    <c:if test="${jabatanNama != i.jabatanNama}" >
	                        <c:set var="jabatanNama" value="${i.jabatanNama}" />
	                        <optgroup label="${jabatanNama}" />
	                    </c:if>
	                    <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
	                </c:forEach>--%>
                        <stripes:option label="Pilih Urusan..."  value="0" />
                        <c:forEach items="${senaraiUrusanPendaftaran}" var="i" >
                            <stripes:option value="${i.kod}" >${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
	            </stripes:select>
	            
	        </nobr>
	        </p>
	
		    <p>
		        <label>&nbsp;</label>
		        <stripes:submit name="Cancel" value="Batal" class="btn" />
		        <stripes:submit name="Step6c" value="Seterusnya" class="btn" />
		    </p>
	
    </fieldset>
    
    <stripes:submit name="updateUrusanJabatan" style="display:none;" />
	
    <stripes:hidden name="selectedItem" />
    <stripes:hidden name="mod" />
    <!-- CACHING OF UrusanValue -->
    <stripes:hidden name="senaraiUrusan[0].idPermohonanSebelum" />
    <stripes:hidden name="senaraiUrusan[0].amaun1" />
    <stripes:hidden name="senaraiUrusan[0].tarikh1" />
    <stripes:hidden name="senaraiUrusan[0].nilai1" />
    <stripes:hidden name="senaraiUrusan[0].cajPengecualian" />
    <stripes:hidden name="senaraiUrusan[0].cajPelepasan" />
    <stripes:hidden name="senaraiUrusan[0].cajNotis" />
    <stripes:hidden name="senaraiUrusan[0].cajFail" />
    <stripes:hidden name="senaraiUrusan[0].cajLain" />
    
    <stripes:submit name="updateUrusanJabatan" style="display:none;" />

</stripes:form>

</body>
</html>