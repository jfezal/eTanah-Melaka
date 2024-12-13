<%--
    Document   : editCukai
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
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
<link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        
        $('input:text').each(function(){
            $(this).focus(function() { $(this).addClass('focus')});
            $(this).blur(function() { $(this).removeClass('focus')});
        });

        
    });

    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 45 || charCode > 57 || charCode == 47))
            return false;

        return true;
    }

</script>
<script language="javascript">
    function save(event, f, idHakmilik)
    {
        var cukai = document.form1.cukai.value;
        if ((cukai == ""))
        {
            alert('Sila Masukkan Cukai Baru ');
            document.form1.cukai.focus();
            return false;
        }

         $.get('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraCukaiKelompok&idHakmilik='+idHakmilik,
        function(data){
            if(cukai < data)
            {
                alert('Cukai yang dimasukkan kurang dari RM '+data);
                document.form1.cukai.focus();
                return false;
            }
            else
            {
                var q = $(f).formSerialize();
                var url = f.action + '?' + event+'&idHakmilik='+idHakmilik ;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                      },'html');
                }
            }, 'html');      
                   
    }


    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "123456789.";
        var strReturn = "0";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Cukai Baru
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <br>
            <p>
                <label>Nilai Cukai Baru (RM):</label><s:text id="cukai" onkeypress="return isNumberKey(event)" name="permohonanPembetulanHakmilik.cukai" />
            </p>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div>
                            <br>

                            <s:button name="saveCukai" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idHakmilik}')"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
            <br/>

        </fieldset>
    </div>

</s:form>


