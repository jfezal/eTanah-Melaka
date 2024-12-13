<%-- 
    Document   : kemaskini_hakmilik_permohonan
    Created on : Aug 12, 2010, 10:10:07 AM
    Author     : fikri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $('.idHakmilikBaru').each(function(index){
            $(this).blur(function(){
                validateHakmilik(index+1);
            });
        });
    });

    function doCheckHakmilik (hakmilik) {
        var valid = true;
        $('.hakmilikLama').each(function(index){            
            if (hakmilik.trim() == $(this).val()) {
                valid = false;
            }
        });
        return valid;
    }


    function validateHakmilik(idxHakmilik){        
        var val = $("#hakmilik" + idxHakmilik).val();        
        frm = this.form;
        if (val == null || val == "") return;
        val = val.toUpperCase();        
        if (!doCheckHakmilik (val)) {
            $("#hakmilik" + idxHakmilik).val("");
            alert ('Hakmilik sudah dipilih.');
            return;
        }
        
        $("#hakmilik" + idxHakmilik).val(val);
        $.get("${pageContext.request.contextPath}/daftar/check_idhakmilik?doCheckHakmilik&idHakmilik=" + val,
        function(data){
            if(data == '1'){
                $("#msg" + idxHakmilik).html("OK");
            } else if(data =='0'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                $("#hakmilik" + idxHakmilik).val("");
                alert("ID Hakmilik " + val + " tidak wujud!");
                // $("#msg" + idxHakmilik).html("<font color=red>ID Hakmilik " + val + " tidak wujud!</font>");
            } else if(data =='2'){
                // disable if invalid Hakmilik
                // $("#collectPayment").attr("disabled", "true");
                //$("#hakmilik" + idxHakmilik).val("");
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
            } else if (data == '3'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            } else{
                alert(data);
            }
        });
    }

    function doSubmit(event, form){
        var q = $(form).formSerialize();
        var url = form.action + '?' + event;        
        $.ajax ({
            type : 'POST',
            url : url,
            dataType : 'html',
            data : q,
            error : function (xhr, ajaxOptions, thrownError){},
            success : function (data){
                $('#page_div',opener.document).html(data);
                self.close();
            }
        });        
    }

</script>

<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">        
        <div class="subtitle ">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Permohonan
                </legend>
                <br/>
                <font color="red" size="2">&nbsp;&nbsp;Sila kemaskini hakmilik yang terlibat sahaja.</font>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanKemaskini}"  cellpadding="0" cellspacing="0"
                                   requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik">
                            <s:hidden name="hakmilikLama" id="hakmilikLama${line_rowNum}" value="${line.hakmilik.idHakmilik}" class="hakmilikLama"/>
                            ${line.hakmilik.idHakmilik}
                        </display:column>
                        <display:column title="ID Hakmilik Baru">
                            <s:text name="idHakmilikBaru" id="hakmilik${line_rowNum}" class="idHakmilikBaru"/>
                            <div id="msg${line_rowNum}"/>
                        </display:column>                        
                    </display:table>
                </div>
                <div align="center">
                    <p>
                        <label></label>&nbsp;
                        <s:button name="saveHakmilik" value="Simpan" onclick="doSubmit(this.name, this.form);" class="btn"/>
                        <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                </div>
        </div>
    </s:form>
</div>