<%-- 
    Document   : kemasukan_tanahrizab
    Created on : 30-Mar-2010, 10:15:33
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
        self.opener.refreshRizab();
        self.close();}

function validation() {
        if($("#nolitho").val() == ""){
            alert('Sila masukkan " No Lembaran Piawai " terlebih dahulu.');
            $("#nolitho").focus();
            return true;
        }
        if($("#daerah").val() == ""){
            alert('Sila pilih " Daerah " terlebih dahulu.');
            $("#daerah").focus();
            return true;
        }
        if($("#bpm").val() == ""){
            alert('Sila pilih " Bandar/Pekan/Mukim " terlebih dahulu.');
            $("#bpm").focus();
            return true;
        }
        if($("#cawangan").val() == ""){
            alert('Sila pilih " Cawangan " terlebih dahulu.');
            $("#cawangan").focus();
            return true;
        }
        if($("#rizab").val() == ""){
            alert('Sila pilih " Jenis Rizab " terlebih dahulu.');
            $("#rizab").focus();
            return true;
        }
         if($("#nolot").val() == ""){
            alert('Sila masukkan " No Lot " terlebih dahulu.');
            $("#nolot").focus();
            return true;
        }
         if($("#lokasi").val() == ""){
            alert('Sila masukkan " Lokasi " terlebih dahulu.');
            $("#lokasi").focus();
            return true;
        }
         if($("#nowarta").val() == ""){
            alert('Sila masukkan " No Warta " terlebih dahulu.');
            $("#nowarta").focus();
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
                    self.opener.refreshRizab();
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


  
  </script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<div class="subtitle">
    <%--<s:form beanclass="etanah.view.stripes.common.PihakBerkepentinganActionBean" >--%>
         <s:form beanclass="etanah.view.stripes.pengambilan.LaporanTanahActionBean" >
             <fieldset class="aras1">
            <legend>
                Kemasukan Tanah Rizab
            </legend>
            <p>
                <label for="lembaranpiawai">No. Lembaran Piawai :</label>
                <s:text name="noLitho" size="20" id="nolitho" />
            </p>
            <p>
                <label>  Daerah :</label>
                <s:select name="daerah.kod" id="daerah">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodDaerah}" label="nama" value="kod" sort="nama"  />
                </s:select>
            </p>
             <p>
                <label>  Bandar/Pekan/Mukim :</label>
                <s:select name="bandarPekanMukim.kod" id="bpm"  >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
            <p>
                <label>  Cawangan :</label>
                <s:select name="cawangan.kod" id="cawangan">
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodCawangan}" label="name" value="kod" sort="name" />
                </s:select>
            </p>
            <p>
                <label>Jenis Rizab :</label>
                <s:select name="rizab.kod" id="rizab" >
                    <s:option value="">--Sila Pilih--</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" sort="nama" />
                </s:select>
            </p>
             <p>
                <label>No Lot :</label>
                <s:text name="noLot" size="20" id="nolot"/>
            </p>
             <p>
                <label for="lokasi">Kedudukan Tanah :</label>
                <s:text name="lokasi" size="40" id="lokasi"/>
            </p>
             <p>
                <label for="nowarta">No. Warta :</label>
                <s:text name="noWarta" size="20" id="nowarta"/>
            </p>
             <p>
                <label for="tarikhwarta">Tarikh Warta :</label>
                <s:text name="tarikhWarta" id="datepicker" class="datepicker" size="12" />
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button name="searchtrizab" value="Simpan" class="btn" onclick="save1(this.name, this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>


         </s:form>
</div>