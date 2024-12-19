<%-- 
    Document   : kemasukan_pengawal_pegawai
    Created on : 08-Dec-2010, 17:54:20
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

        function savePenjaga(event, f){
            if(validation()){

            }
            else{
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    <%--self.opener.refreshPageHakmilik();--%>
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

         <s:form beanclass="etanah.view.stripes.pengambilan.MaklumatHakmilikActionBean" >
             <fieldset class="aras1">
            <legend>
                 Maklumat Pegawai Pengawal
            </legend>
            <p>
                <label for="nolot">Nama Pegawai Pengawal/Agensi/Badan :</label>
                <s:text name="namajaga" size="20" id="nolot"/>
            </p>
            <p>
                <label for="alamat">Alamat Surat-Menyurat:</label>
                <s:text name="rizab.jagaAlamat1" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat2" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat3" size="40"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="rizab.jagaAlamat4" size="40"/>
            </p>
            <p>
                <label>Poskod :</label>
                <s:text name="rizab.jagaPoskod" size="40" maxlength="5"/>
            </p>

            <p>
                <label for="Negeri">Negeri :</label>
                <s:select name="rizab.jagaNegeri.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>No.Telefon :</label>
                <s:text name="jagaTel" maxlength="11" id="notelefon"  /><font color="red">*</font>

            </p>
            <p>
                <label>No.Fax :</label>
                <s:text name="jagaFax" maxlength="11" id="notelefon"  /><font color="red">*</font>

            </p>
            <p>
                <label>Email :</label>
                <s:text name="jagaEmail" size="40" maxlength="100" />

            </p>
            <s:button name="simpanPengawal" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form);"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </fieldset>



         </s:form>
</div>