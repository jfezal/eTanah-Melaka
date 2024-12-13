<%-- 
    Document   : kemaskini_hakmilik_permohonan
    Created on : Aug 12, 2010, 10:10:07 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.idHakmilikBaru').each(function(index) {
            $(this).blur(function() {
                validateHakmilik(index + 1);
            });
        });
    });

//    function doCheckHakmilik (hakmilik) {
//        var valid = true;
//        $('.hakmilikLama').each(function(index){            
//            if (hakmilik.trim() == $(this).val()) {
//                valid = false;
//            }
//        });
//        return valid;
//    }


    function validateHakmilik(idxHakmilik) {
        var val = $("#hakmilik" + idxHakmilik).val();
        frm = this.form;
        if (val == null || val == "")
            return;
        val = val.toUpperCase();
        if (!doCheckHakmilik(val)) {
            $("#hakmilik" + idxHakmilik).val("");
            alert('Hakmilik sudah dipilih.');
            return;
        }

        $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
                function(data) {
                    if (data == '1') {
                        $("#msg" + idxHakmilik).html("OK");
                    } else if (data == '0') {
                        // disable if invalid Hakmilik
                        // $("#collectPayment").attr("disabled", "true");
                        $("#hakmilik" + idxHakmilik).val("");
                        alert("ID Hakmilik " + val + " tidak wujud!");
                        // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
                    } else if (data == '2') {
                        // disable if invalid Hakmilik
                        // $("#collectPayment").attr("disabled", "true");
                        //$("#hakmilik" + idxHakmilik).val("");
                        $("#msg" + idxHakmilik).html("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                        alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                    } else if (data == '3') {
                        $("#hakmilik" + idxHakmilik).val("");
                        alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
                    } else if (data.charAt(0) == '4') {
                        alert(data);
                        $("#hakmilik" + idxHakmilik).val("");
                        var str = "Hakmilik " + val + " telah dibatalkan.";
                        if (data.substring(2).length > 0)
                            str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                        alert(str);
                        $("#msg" + idxHakmilik).html(str);
                    } else {
                        alert("Unknown reply: " + data);
                    }
                });

        // check for kaveat
        $.get("${pageContext.request.contextPath}/daftar/check_kaveat?doCheckKaveat&idHakmilik=" + val,
                function(data) {
                    if (data == '0') {
                        // nothing to do
                    } else if (data == '1') {
                        alert("Hakmilik " + val + " mempunyai Kaveat!");
                    }
                });

        alert(msg);

    }



    function saveHakmilik(val, f) {

        if ($('#idHakmilikBaru').val() == "") {
            alert('Sila Masukkan ID Hakmilik');
        }
        else {
            f.action = f.action + '?saveHakmilik&idPermohonan=' + val;
            f.submit();
            self.close();
        }
    }
    function saveAkaun(val, f, id2) {
        alertMsg(val);
        if ($('#noAkaunBaru').val() == "") {
            alert('Sila Masukkan No Akaun');
        }
        else {
            f.action = f.action + '?saveAkaun&noAkaun=' + val;
            f.submit();
            self.close();
        }
    }
    function saveWK(val, f, id2) {

        if ($('#wakilBaru').val() == "") {
            alert('Sila Masukkan No Wakil Kuasa');
        }
        else {
            f.action = f.action + '?saveWk&noAkaun=' + val;
            f.submit();
            self.close();
        }
    }



</script>
<s:messages/>
<s:errors/>
<div class="subtitle displaytag">
    <s:form action="/daftar/betul_hakmilik">
        <s:hidden name="permohonan.idPermohonan"/>
        <s:hidden name="idWakil"/>
        <c:if test="${fn:length(actionBean.hakmilikPermohonanKemaskini) > 0}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik Permohonan
                    </legend>
                    <br/>
                    <font color="red" size="2">Sila kemaskini hakmilik yang terlibat sahaja.</font>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanKemaskini}"  cellpadding="0" cellspacing="0"
                                       requestURI="/daftar/betul_hakmilik" id="line">
                            <display:column title="Bil">${line_rowNum}</display:column>
                            <display:column title="ID Permohonan">
                                <s:hidden name="idPermohonan" id="hakmilikLama${line_rowNum}" value="${line.permohonan.idPermohonan}" class="idPermohonan"/>
                                ${line.permohonan.idPermohonan}
                            </display:column>
                            <display:column title="ID Hakmilik">
                                <s:hidden name="hakmilikLama" id="hakmilikLama${line_rowNum}" value="${line.hakmilik.idHakmilik}" class="hakmilikLama"/>
                                ${line.hakmilik.idHakmilik}
                            </display:column>
                            <display:column title="ID Hakmilik Baru">
                                <s:text name="idHakmilikBaru" id="hakmilik${line_rowNum}" class="idHakmilikBaru" onkeyup="this.value=this.value.toUpperCase();"/>
                                <div id="msg${line_rowNum}"/>
                            </display:column>                        
                        </display:table>
                    </div>
            </div>
            <div align="center">
                <p>
                    <label></label>&nbsp;
                    <%--<s:button name="saveHakmilik" value="simpan" onclick="saveHakmilik(this.form);" class="btn"/>--%>
                    <s:submit name="saveHakmilik" value="simpan" class="btn"/>
                    <%--<s:submit name="Simpan" value="saveHakmilik" class="btn"/>--%>
                    <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                </p>
            </div>
        </fieldset>
    </div>
</c:if>

<!--<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            Maklumat Akaun 
        </legend>
        <br/>
        <font color="red" size="2">Sila kemaskini Akaun yang terlibat sahaja.</font>
        <div class="content" align="center">
<display:table class="tablecloth" name="${actionBean.akaun}"  cellpadding="0" cellspacing="0"
               requestURI="/daftar/betul_hakmilik" id="line">
    <display:column title="Bil">${line_rowNum}</display:column>
    <display:column title="No Akaun Lama">
        <s:hidden name="noAkauanLama" id="noAkauanLama${line_rowNum}" value="${actionBean.akaun.noAkaun}" class="noAkauanLama"/>
        ${actionBean.akaun.noAkaun}
    </display:column>
    <display:column title="No Akaun Baru">
        <s:text name="noAkaunBaru" id="hakmilik${line_rowNum}" class="noAkaunBaru" onkeyup="this.value=this.value.toUpperCase();"/>
        <div id="msg${line_rowNum}"/>
    </display:column>                        
</display:table>
</div>
</div>
<div align="center">
<p>
<label></label>&nbsp;
<s:submit name="saveAkaun" value="Simpan" onclick="saveAkaun('${actionBean.akaun.noAkaun}','${line.hakmilik.idHakmilik}', '${actionBean.permohonan.idPermohonan}', this.form);" class="btn"/>
<s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
</p>
</div>
</fieldset>
</div>-->
<c:if test="${fn:length(actionBean.listNoRuj) > 0}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Wakil Kuasa 
            </legend>
            <br/>
            <font color="red" size="2">Sila kemaskini Wakil Kuasa yang terlibat sahaja.</font>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.listNoRuj}" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="Bil" sortable="true">${line_rowNum}
                    </display:column>
                    <display:column property="noRujukan" title="Id Wakil"/>                                    


                    <display:column title="No Wakil Kuasa Baru">
                        <s:text name="wakilBaru" id="wakilBaru{line_rowNum}" class="wakilBaru" onkeyup="this.value=this.value.toUpperCase();"/>
                        <div id="msg${line_rowNum}"/>
                    </display:column> 

                </display:table>
            </div>
    </div>
    <div align="center">
        <p>
            <label></label>&nbsp;
            <s:submit name="saveWk" value="Simpan" onclick="saveWK('${actionBean.listNoRuj}','${actionBean.permohonan.idPermohonan}' this.form);" class="btn"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </div>
</fieldset>
</div>
</c:if>
<c:if test="${fn:length(actionBean.listNoRuj) <= 0}">
    <c:if test="${fn:length(actionBean.hakmilikPermohonanKemaskini) <= 0}">
    <div align="center">
        <p>
            <label></label>&nbsp;
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
        </p>
    </div></c:if></c:if>

</s:form>
</div>