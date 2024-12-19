
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/date.format.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    < !DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
            "http://www.w3.org/TR/html4/loose.dtd" >
            < script >
            function doSearch(f, e) {
                var v = $('#idPerserahan').val();
                var v1 = $('#idPerserahan1').val();
                if (v == '') {
                    alert('Sila masukkan  ID Perserahan/ID Permohonan.');
                    $('#idPerserahan').focus();
                    return;
                }
                if (v1 == '') {
                    alert('Sila masukkan  ID Perserahan/ID Permohonan Yang Kedua');
                    $('#idPerserahan').focus();
                    return;
                }
                f.action = f.action + '?' + e;
                f.submit();
            }
    function simpan(id)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?kemaskiniPenyerah&idMohon=" + id;
        frm.action = url;
        frm.submit();
        self.close();

    }

    function saveHakmilik(val, f) {

        var v = $('#idPerserahan').val();
        var v1 = $('#idPerserahan1').val();
        if (v == '') {
            alert('Sila masukkan  ID Perserahan/ID Permohonan.');
            $('#idPerserahan').focus();
            return;
        }
        if (v1 == '') {
            alert('Sila masukkan  ID Perserahan/ID Permohonan Yang Kedua');
            $('#idPerserahan').focus();
            return;
        }
        var v = $('#idPerserahan').val();
        var v1 = $('#idPerserahan1').val();
        if (v == '') {
            alert('Sila masukkan  ID Perserahan/ID Permohonan.');
            $('#idPerserahan').focus();
            return;
        }
        if (v1 == '') {
            alert('Sila masukkan  ID Perserahan/ID Permohonan Yang Kedua');
            $('#idPerserahan').focus();
            return;
        } else {
            f.action = f.action + '?search=' + val;
            f.submit();
            self.close();
        }
    }
</script>

<s:form beanclass="etanah.view.daftar.utiliti.utilitiBerangkaiActionBean">
    <c:if test="${actionBean.red eq false}">
        <c:if test="${actionBean.red2 eq true}">
            Sila Betulkan Kesalahan Tersebut
        </c:if>
    </c:if>

    <fieldset class="aras1">
        <legend>Utiliti Urusan Berangkai</legend>
        <br>
        <c:if test="${actionBean.green eq true}">
            <center>  Kemasukan Telah Berjaya </center>
        </c:if>
            
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan" id="idPerserahan" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan1" id="idPerserahan1" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan2" id="idPerserahan2" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan3" id="idPerserahan3" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan4" id="idPerserahan4" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 
        <p>
            <label><em>*</em>ID Perserahan/ID Permohonan :</label>
            <s:text name="idPerserahan5" id="idPerserahan5" onkeyup="this.value=this.value.toUpperCase();"/>
        </p> 

        <p>
            <label>&nbsp;</label>
            <s:submit name="search" value="Cari"  onclick="doSubmit(this.form, this.name, 'page_div');" class="btn"/>
            <s:button name="reset" value="Isi Semula" onclick="clearForm(this.form);" class="btn"/>
        </p>
        <br/>
    </fieldset>            
</s:form>
