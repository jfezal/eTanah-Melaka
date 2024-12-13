<%-- 
    Document   : tambahHakmilikSebelum
    Created on : May 24, 2010, 4:32:47 PM
    Author     : mohd.fairul
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
<link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
</style>

<script language="javascript">
    $(document).ready( function(){

//          $('.idHakmilikSebelum').each(function(index){
//            $(this).blur(function(){
//                validateHakmilik(index+1);
//            });
//        });
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $('input').focus(function() {
                    $(this).addClass("focus");
                });

                $('input').blur(function() {
                    $(this).removeClass("focus");
                });

                $('select').focus(function() {
                    $(this).addClass("focus");
                });

                $('select').blur(function() {
                    $(this).removeClass("focus");
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
                $("#msg" + idxHakmilik).html("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
                alert("Cukai untuk Hakmilik " + val + " masih belum dijelaskan!");
            } else if (data == '3'){
                $("#hakmilik" + idxHakmilik).val("");
                alert("Status hakmilik Provisional. Tiada Urusniaga / Kaveat Persendirian / Lien dibenarkan.");
            } else if (data.charAt(0) == '4'){
                alert(data);
            	$("#hakmilik" + idxHakmilik).val("");
                var str = "Hakmilik " + val + " telah dibatalkan.";
                if (data.substring(2).length > 0) str += " Hakmilik sambungan ialah " + data.substring(2) + ".";
                alert(str);
            	$("#msg" + idxHakmilik).html(str);
            } else{
                alert("Unknown reply: " + data);
            }
        });

    // check for kaveat
    $.get("${pageContext.request.contextPath}/daftar/check_kaveat?doCheckKaveat&idHakmilik=" + val,
            function(data){
                if(data == '0'){
                    // nothing to do
                } else if(data =='1'){
                    alert("Hakmilik " + val + " mempunyai Kaveat!");
                }
            });

    alert(msg);

}


        function save(event, f, idH)
     {



                var q = $(f).formSerialize();
                var url = f.action + '?' + event+'&idH='+idH ;

                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                                     },'html');

                $.prompt('Maklumat Berjaya Disimpan.',{
                    buttons:{Ok:true},
                    prefix:'jqismooth',
                    submit:function(v,m,f){
                       self.close();
                    }
                });

     }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Sebelumkini
            </legend>
            <p style="color:red">
                *Isi ID Hakmilik Sebelumkini kemudian tekan butang simpan.<br/>

            </p>
            <div class="content" align="center" id="tanahMilik">        
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik">
                            <s:hidden name="hakmilikLama" id="hakmilikLama${line_rowNum}" value="${line.hakmilik.idHakmilik}" class="hakmilikLama"/>
                            ${line.hakmilik.idHakmilik}
                    </display:column>
                  <display:column title="ID Hakmilik Sebelumkini">
                        <s:text name="idHakmilikSebelum" id="hakmilik${line_rowNum}" class="idHakmilikSebelum"/>
                        <div id="msg${line_rowNum}"/>
                    </display:column>
                </display:table>
            </div>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <s:button name="saveBetulSebelum" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.hakmilik.idHakmilik}')"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
            <br/>

        </fieldset>
    </div>

</s:form>
