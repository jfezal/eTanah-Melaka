<%--
    Document   : kod_dato_Lembaga
    Created on : Sep 5, 2010, 12:44:56 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
function removeKodDato(val){
        //alert("==="+val);
        if(confirm('Adakah pasti untuk hapus Kod :"'+val+'" dalam senarai?')) {
            var url = '${pageContext.request.contextPath}/hasil/kodDatoLembaga?deleteKod&kod=' + val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                refreshPageDato();
            },'html');
        }
        }

     function editKodDato(id){
     //alert("editKodSekolah");
        window.open("${pageContext.request.contextPath}/hasil/kodDatoLembaga?showEditKod&kod="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
     function create(){
        window.open("${pageContext.request.contextPath}/hasil/kodDatoLembaga?createKod", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
    function refreshPageDato(){
        var searchKod = $(".searchKod").val();
        var searchNama = $(".searchNama").val();
        var kodNegeri = $(".kodNegeri").val();
        var q = $('#form1').serialize();
        var url = document.f1.action + '?refreshpage&searchKod='+searchKod+'&searchNama='+searchNama+'&kodNegeri='+kodNegeri;// + event;
        window.location = url+q;

    }
function test(f) {
        $(f).clearForm();
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="f1" beanclass="etanah.view.stripes.hasil.KodDatoLembagaActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian Kod Dato' Lembaga</legend>
            <p>
                <label>Kod :</label>
                <s:text name="searchKod"  value="" size="25" maxlength="8" id="searchKod" class="searchKod" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nama :</label>
                <s:text name="searchNama" value="" size="40" maxlength="60" id="searchNama" class="searchNama" onchange="this.value = this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Negeri :</label>
                <s:select name="kodNegeri" class="kodNegeri" id="kodNegeri" style="width:175px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
           </p>
           <p>
               <label>&nbsp;</label>
               &nbsp;
           </p>
           <p align="center">
               <s:submit name="search" value="Cari" class="btn"/>
               <s:button name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
           </p>

          <%--<table border="0" bgcolor="" width="100%" >
            <tr>
                <td align="center">
                    <s:submit name="search" value="Cari" class="btn"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                </td>
            </tr>
       </table>--%>
        </fieldset>
    </div>
  <c:if test="${Show}">
   <div class="content" align="center">
       <fieldset class="aras1">
           <legend>Maklumat Kod Dato' Lembaga</legend>
           <p align="center">
               <s:button name="createKod" value="Tambah" class="btn" onclick="create();" /> &nbsp;
           </p>
            <%--<table width="400" border="0" cellpadding="0" cellspacing="0" id="f1table" align="center">
                <tr id="trr1">
                    <td><s:button name="createKod" value="Tambah" class="btn" onclick="create();" /> &nbsp;</td>
                </tr>
            </table>--%>

            <display:table name="${actionBean.senaraiRujukan}" class="tablecloth" id="row">
                <display:column title="Bil.">${row_rowNum}</display:column>
              <display:column title="Kod" property="kod"/>
              <display:column title="Nama" property="nama"/>
              <display:column title="Alamat" class="alamat">
                        ${row.alamat.alamat1}<c:if test="${row.alamat.alamat2 ne null}">,</c:if>
                        ${row.alamat.alamat2}<c:if test="${row.alamat.alamat3 ne null}">,</c:if>
                        ${row.alamat.alamat3}<c:if test="${row.alamat.alamat4 ne null}">,</c:if>
                        ${row.alamat.alamat4}<c:if test="${row.alamat.poskod ne null}">,</c:if>
                        ${row.alamat.poskod}<c:if test="${row.alamat.negeri ne null}">,</c:if>
                        ${row.alamat.negeri.nama}
              </display:column>
              <display:column title="No. Tel" class="">
                        ${row.noTel1}<c:if test="${row.noTel2 ne null}">,</c:if>
                        ${row.noTel2}
              </display:column>
              <display:column title="No. Fax" property="nofax"/>
              <display:column title="Email" property="email"/>
              <display:column title="Kemaskini" style="">
                    <div align="center">
                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                             id='rem_${row_rowNum}' onclick="editKodDato('${row.kod}');return false;" onmouseover="this.style.cursor='pointer';">
                    </div>
              </display:column>
             <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${row_rowNum}' onclick="removeKodDato('${row.kod}')">
                    </div>
             </display:column>
            </display:table>
            </fieldset>
        </div>
    </c:if>
</s:form>