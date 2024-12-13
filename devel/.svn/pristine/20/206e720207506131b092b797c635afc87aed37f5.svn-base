<%--
    Document   : popup_edit_kawasan
    Created on : July  12, 2012, 12:41:57 PM
    Author     : ctzainal
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

    function validate()
    {
        a = document.test.id ;

        if (document.test.id.value==null||document.test.id.value=="")
        {

            alert("Sila masukkan id permohonan");
            a.focus()
            return false;
        }
        else
        {

            return true ;
        }

    }

    function test1(){

        self.close();







    }
    function openLain_lain(val){
        if(val == '99'){
            $('#catatanKwsn').show();
        }else{
            $('#catatanKwsn').hide();
        }
    }
    function save1(event, f){
        alert("Adakah anda pasti untuk simpan?");
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $("#DalamKawasanDiv",opener.document).replaceWith($('#DalamKawasanDiv', $(data)));
            self.opener.refreshDalamKawasan();
            self.close();
        },'html');

    }
    $(document).ready(function() {
        maximizeWindow(),$('#catatanKwsn').hide(),$(".datepicker").datepicker({dateFormat: 'dd/mm/yy'}),
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.LaporanPelukisPelanActionBean" name="test">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
        <fieldset class="aras1">

            <legend>
                Tanah Dipohon Berada Dalam Kawasan :
            </legend>

            <div class="content">

                <s:hidden name="id2" value="id"/>
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <s:hidden name="idMohonlaporKws" id="idMohonlaporKws" />




                <p>
                    <label>Jenis Rizab :</label>
                    <s:select name="kodRizab" id="kodRizab" onchange="openLain_lain(this.value)">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodRizab}" label="nama" value="kod" />
                    </s:select>&nbsp;
                </p>

                <p id="catatanKwsn">
                    <label>Catatan :</label>
                    <s:text name="catatan" id="catatan" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label>No Warta :</label>
                    <s:text name="addnoWarta" id="addnoWarta" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

                <p>
                    <label>Tarikh Warta :</label>
                    <s:text name="addtarikhWarta"  class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="addtarikhWarta"/>&nbsp;
                    <font color="red" size="1">cth : hh / bb / tttt</font>
                </p>


                <p>
                    <label>No Pelan Warta :</label>
                    <s:text name="addnoPelanWarta" id="addnoPelanWarta" size="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>



                <p><label>&nbsp;</label>
                    <s:button name="editLa" value="Simpan" class="btn" onclick="save1(this.name, this.form);"/>
                    &nbsp;<s:submit name="tutupPermohonan" value="Tutup" class="btn" onclick="test1();"/>
                </p>

            </div>
        </fieldset>
    </div>
</s:form>
