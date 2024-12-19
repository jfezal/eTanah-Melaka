<%-- 
    Document   : sokongan
    Created on : Oct 23, 2009, 11:33:25 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function dateValidation(value){
        var vsplit = value.split('/');
        var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
        var today = new Date();
        var sdate = new Date(fulldate);
        if (sdate > today){
            alert("Tarikh yang dimasukkan tidak sesuai.");
            $('#dateRujukan').val("");
        }
    }

    function validation() {
        if($("#agensi").val() == ""){
		alert('Sila pilih " Disahkan Oleh " terlebih dahulu.');
  		$("#agensi").focus();
		return true;
	}
	if($("#nombor").val() == ""){
		alert('Sila masukkan " Nombor Rujukan/Pejabat " terlebih dahulu.');
  		$("#nombor").focus();
		return true;
	}
        if($("#dateRujukan").val() == ""){
		alert('Sila masukkan " Tarikh " terlebih dahulu.');
  		$("#dateRujukan").focus();
		return true;
	}
        return false;
    }

    function save(event, f){
            if(validation()){

            }
            else{
                doSubmit(f, event, 'page_div');
            }
        }
</script>

<s:form name="sokongan" beanclass="etanah.view.stripes.hasil.SokonganActionBean">
    <div class="subtitle">
        <br>

        <fieldset class="aras1">
            <legend>
                Maklumat Sokongan
            </legend>
            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
            <p>
                <label><font color="red">*</font>Disahkan Oleh :</label>
                <s:select id="agensi" name="agensi" style="width:500px;">
                    <s:option value="">Sila Pilih...</s:option>
                    <s:options-collection collection="${actionBean.senaraiKodAgensi}" label="nama" value="kod" />
                </s:select>
            </p>
            <p>
                <label><font color="red">*</font>Nombor Rujukan/Pejabat :</label>
                <s:text id="nombor" name="permohonanRujukanLuar.noRujukan" style="width:160px;" /><s:hidden name="permohonanRujukanLuar.idRujukan" />
            </p>
            <p>
                <label><font color="red">*</font>Tarikh :</label>
                <s:text name="dateRujukan" id="dateRujukan" class="datepicker" formatPattern="dd/MM/yyyy" onchange="dateValidation(this.value)"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <p>
                <label>Ulasan :</label>
                <s:textarea name="permohonanRujukanLuar.ulasan" cols="55" rows="10"/>
            </p>
            <div style="${actionBean.display}">
                <p align="right">
                    <s:button class="btn" onclick="save(this.name, this.form);" name="saveOrUpdate" value="Simpan"/>&nbsp;
                    <%--<s:reset class="btn" name="reset" value="Isi Semula"/>--%>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>