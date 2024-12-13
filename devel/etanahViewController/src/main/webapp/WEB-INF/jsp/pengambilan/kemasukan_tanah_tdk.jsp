<%-- 
    Document   : kemasukan_tanah_tdk
    Created on : 06-Dec-2010, 17:58:04
    Author     : nordiyana
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>


<script type="text/javascript">


$(document).ready( function(){
$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
});
function save(){
        self.opener.refreshPageTanahRizab();
        self.close();}

function validation() {
        if($("#idhakmilik").val() == ""){
            alert('Sila masukkan " No H/M " terlebih dahulu.');
            $("#idhakmilik").focus();
            return true;
        }

    }

        function save1(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.opener.refreshPageHakmilik();
                    self.close();
                },'html');
            }
        }
          $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });

  function showCukaiValue() {
     $('#cukai').show();
    }
function hideCukaiValue() {
    $('#cukai').hide();
    }

     function filterDaerah(kodDaerah){
                           var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?penyukatanBPM&daerah='+kodDaerah;
                           $.get(url,
                           function(data){
                               $('#daerah').html(data);
                           },'html');
                       }



  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">

         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatHakmilikActionBean" >
             <fieldset class="aras1">
            <legend>
                 Tanah Tidak Dapat Dikesan (TDK)
            </legend>
            <p>
                <label>Jenis Rizab :</label>
                <s:select name="kodRizab.kod" id="rizab" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>Daerah :</label>
                <s:select name="daerah.kod" id="daerah" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama" />
                </s:select>

                    <label>  Daerah :</label>
                            <s:select name="daerah" id="daerah" style="width:210px;" onchange="filterDaerah(this.value);">
                                <s:option value="">--Sila Pilih--</s:option>
                                <%--<s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />--%>
                                <c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >
                                    <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>
                                </c:forEach>
                            </s:select>
            </p>
           <p>
                <label>Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bandarPekanMukim" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label for="nolot">No. Lot :</label>
                <s:text name="noLot" size="20" id="noLot"/>
            </p>
            <p>
                <label for="Luas diambil">Luas Diambil :</label>
                <s:text name="luasAmbil" size="20" id="luasAmbil"/>
                <s:select name="kodUnitLuas" id="kodUnitLuas${line_rowNum-1}" onchange="validateKodUnitLuas(this,${line_rowNum - 1})"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                        </s:select>
            </p>
            <p>
                <label for="Luas">Luas Sebenar :</label>
                <s:text name="Luas" size="20" id="luas"/>
                   <s:select name="kodUnitLuas" id="kodUnitLuas${line_rowNum-1}" onchange="validateKodUnitLuas(this,${line_rowNum - 1})"><font color="red">*</font>
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="H">Hektar</s:option>
                                    <s:option value="M">Meter Persegi</s:option>
                        </s:select>
            </p>
            <p>
                <label for="cukai">Cukai :</label>&nbsp;
                <s:radio name="adaCukai" value="A" id="idCukai" onclick="showCukaiValue();"/>Ada
                <s:radio name="adaCukai" value="T" id="idCukai" onclick="hideCukaiValue();"/>Tiada
            </p>

            <p>
                <label for="cukai">Nilai Cukai (RM) :</label>
                <s:text name="cukai" size="30" id="cukai"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:button name="simpanTanahTDK" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>