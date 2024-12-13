<%-- 
    Document   : maklumat_pertukaran_urusan
    Created on : April 16, 2011, 11:11:11 PM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script language="javascript" type="text/javascript">
    
    
    $(document).ready(function() {
        var i = $('#seksyen').val();
        var kodUrusan = $('#kodUrusan').val();
        document.getElementById("seksyen").value = kodUrusan;
        //alert(i);
        //alert(kodUrusan);

    });
    
    function validateForm(){
        if($('#seksyen').val()=="")
        {
            alert("Sila pilih Kod Urusan Baru");
            $('#seksyen').focus();
            return false;
        }
        return true;
    }

    function validatePertukaran(){
        return true;
    }

    function findSeksyenKodUrusan(){
        var textKodUrusanKod = document.getElementById('seksyen');
        if (textKodUrusanKod.value.length > 0){
            var selectKodUrusan = document.getElementById('kodUrusan');
            selectKodUrusan.selectedIndex = 0;
            var kod = textKodUrusanKod.value.toUpperCase();
            for (var i = 0; i < selectKodUrusan.options.length; ++i){
                if (selectKodUrusan.options[i].value == kod){
                    selectKodUrusan.selectedIndex = i;
                    break;
                }
            }
            if (selectKodUrusan.selectedIndex == 0){
                alert('Kod Urusan ' + kod + ' tidak dijumpai.');
            }
        }
    }
    
    function doTukarSeksyen(e, f) {
        if(confirm('Adakah anda pasti? Sila semak dokumen terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }
</script>


<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPertukaranSeksyenActionBean">
    <s:messages/>
    <c:if test="${edit}">
        <div class="subtitle">       
            <fieldset class="aras1">
                <legend>Maklumat Penukaran Seksyen</legend>
                <p>
                    <label>Kod Urusan Lama :</label>
                    ${actionBean.permohonan.kodUrusan.kod} - ${actionBean.permohonan.kodUrusan.nama} &nbsp;
                </p>
                <p>
                    <label><em>*</em>Kod Urusan Baru :</label>
                    <input name="seksyen" id="seksyen" onblur="javascript:findSeksyenKodUrusan(this.value)" onkeyup="this.value=this.value.toUpperCase();" size="3"/>&nbsp;
                    <s:select name="kodUrusan" id="kodUrusan" style="width:500px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.senaraiUrusan}" label="nama" value="kod" sort="kod" />
                    </s:select>&nbsp;
                </p>
            </fieldset>
            <br>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div')"/>
                <%--<s:button name="initiateTask" id="selesai" value="Selesai" class="btn" onclick="if(validatePertukaran())doSubmit(this.form, this.name,'page_div')"/>--%>
                <%--<s:button name="initiateTask" value="Selesai" class="btn" onclick="doTukarSeksyen(this.name, this.form);"/>--%>
            </p>
        </div>
    </c:if>

</s:form>



