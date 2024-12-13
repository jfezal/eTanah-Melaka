<%-- 
    Document   : janaGeranPeringkatNB
    Created on : Nov 14, 2016, 1:20:08 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    function selectAll(a, clazz) {
        var len = $('.' + clazz).length;
        for (var i = 1; i <= len; i++) {
            var id = clazz + i;
            var c = document.getElementById(id);
            c.checked = a.checked;
        }
    }

    function appendAutoAll(intIndex) {
        var val = $('#idHakmilikDari' + intIndex).val();
        if (val == '') {
            document.getElementById("bilHakmilikSiri0").value = "";
        }
        else if (val != '') {
            appendAuto(val, intIndex);
        }
    }

    function appendAuto(val, id) {
        var bil = $('#bilHakmilikSiri' + id).val();
        var len = val.length;
        var intIndex = 0;
        var pre = "";

        if (val != '') {
            val = val.toUpperCase();
            $('#idHakmilikDari' + id).val(val);
            bil = bil - 1;
            if (parseInt(bil, 10) > 0) {
                for (intIndex = len - 1; intIndex >= 0; intIndex--) {
                    var c = val.charAt(intIndex);
                    if (c >= '0' && c <= '9') {
                        pre = c + pre;
                    } else {
                        break;
                    }
                }

                var h = val.substring(0, intIndex + 1);//temp                
                var val2 = parseInt(pre, 10) + parseInt(bil);
                var len = (pre.length - String(val2).length);
                if (String(val2).length < pre.length) {
                    for (var i = 0; i < len; i++) {
                        val2 = "0" + val2;
                    }
                }

                h = h + val2;
                if (!isNaN(val2)) {
                    $('#idHakmilikHingga' + id).val(h);
                }
            }
        } else {
            $('#idHakmilikHingga' + id).val('');
        }
    }

    function validateHakmilikBersiri(idx) {      
                var dr = $("#idHakmilikDari" + idx).val().toUpperCase();
                var ke = $("#idHakmilikHingga" + idx).val().toUpperCase();
                var val1 = $("#idHakmilikDari" + idx).val().toUpperCase();
                var val2 = $("#idHakmilikHingga" + idx).val().toUpperCase();
                $("#idHakmilikDari" + idx).val(val1);
                $("#idHakmilikHingga" + idx).val(val2);                
                frm = this.form;
                if (dr == null || dr == "" || ke == null || ke == ""){
                  alert("Sila masukkan maklumat yang berkenaan");
                  return;
                }  
                
                var bilBersiri =  $("#bilHakmilikSiri0").val(); 
                var len = $('.nama').length;
                
                  for (var i = 1; i <= len; i++) {
                        var stringDepan = $('#chkbox' + i).val().replace("-" + i, "");
            var pilih1 = $('#chkbox' + i).val().replace(stringDepan + "-", "");
            if (dr == pilih1.trim())
            {
                document.getElementById("chkbox" + i).checked = true;
                var total = i + (bilBersiri - 1);
                for (var a = i; a <= total; a++) {
                    document.getElementById("chkbox" + a).checked = true;
                }
            }
        }
    }
    
    function isNumberKey(evt)
    {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }

    function janaGeran(f) {
        var q = $(f).formSerialize();
        var len = $('.nama').length;
        var param = '';
        var DELIM = ',';
        doBlockUI();

        for (var i = 1; i <= len; i++) {
            var ckd = $('#chkbox' + i).is(":checked");
            if (ckd) {
                param = param + DELIM + $('#chkbox' + i).val().replace("-"+i, "");
            }
        }

        if (param == '') {
            alert('Tiada Hakmilik yang Dipilih.');
            doUnBlockUI();
            return;
        }
        var url = '${pageContext.request.contextPath}/strata/janaGeranNB?janaGeran&param=' + param;
        $.get(url,
                function(data) {
                    $('#page_div').html(data);
                    doUnBlockUI();
                });
    }
</script>    
<s:form beanclass="etanah.view.strata.JanaGeranBerperingkatNBActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Penjanaan Geran Strata</legend>
            <span style="font-weight:normal;color: black" class=instr>
                Sila pilih hakmilik yang ingin dijana
            </span>
            <p align="center">                
                Pilihan No dari
                <s:text name="idHakmilikDari" size="5" id="idHakmilikDari0" onkeypress="return isNumberKey(event)"/>
                hingga
                <s:text name="idHakmilikHingga" size="5" id="idHakmilikHingga0" onkeyup="this.value=this.value.toUpperCase();" readonly="true" disabled="true"/>
                <input type="button" value="Tanda Pilihan" onclick="validateHakmilikBersiri(0)" class="btn" />   
                <br>
             </p> 
             <p align="left">   
                <label>&nbsp;</label>Bil. Pilihan 
                <s:text name="bilHakmilikSiri" id="bilHakmilikSiri0" size="5" onchange="appendAutoAll('0');" onblur="appendAutoAll('0');" maxlength="3" onkeypress="return isNumberKey(event)"/>                             
             </p> 
            <p>
                <label>&nbsp;</label>
                <s:textarea name="listHakmilikSiri" id="txtSiri" style="display:none"/>
            </p>
            </p></p> 
            <div align="left">     
                <p>                                       
                    <s:button name="" value="Jana Geran" class="btn" onclick="janaGeran(this.form);"/>
                    <s:reset name="RESET" value="Isi Semula" class="btn"/>   
                </p>
            </div>
            <br>            
            <div class="content" align="center">                
                <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTerlibat}" requestURI="/strata/janaGeranNB" cellpadding="0" cellspacing="0" id="line">
                    <%--<display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this,\"chkbox\");'/>">--%>
                    <display:column title="">    
                        <s:checkbox name="checkbox" id="chkbox${line_rowNum}" value="${line.idHakmilik}-${line_rowNum}" class="chkbox"/>
                    </display:column>
                    <display:column title="Pilihan</br>No">                        
                        <c:out value="${line_rowNum}"/>
                    </display:column>
                    <display:column property="idHakmilik" title="Hakmilik Strata" class="nama"/>
                </display:table>
            </div>
            </br>            
        </fieldset>
    </div>
</s:form>


