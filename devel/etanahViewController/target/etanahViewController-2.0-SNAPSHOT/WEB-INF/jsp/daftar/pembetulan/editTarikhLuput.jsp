<%--
    Document   : editTarikhLuput
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : wan.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

</script>
<script language="javascript">
    function save(event, f, idHakmilik)
    {
        var tarikhluputbaru = document.form1.tarikhluputbaru.value;
        if ((tarikhluputbaru == ""))
        {
            alert('Sila pilih Tarikh Luput Baru ');
            document.form1.tarikhluputbaru.focus();
            return false;
        }

        var q = $(f).formSerialize();
        var url = f.action + '?' + event+'&idHakmilik='+idHakmilik ;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
              
    }
</script>


<script>

   
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>

    <s:messages />
    <s:errors />

    <div class="subtitle">

        <fieldset class="aras1">
            <legend>
                Maklumat Tarikh Luput Baru
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <br>
            <p>
                <label>Tarikh Luput Baru :</label><s:text class="datepicker" formatPattern="dd/MM/yyyy" name="tarikhluputbaru" />&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </p>
            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div>
                            <br>

                            <s:button name="saveTarikhLuput" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.idHakmilik}')"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
            <br/>

        </fieldset>
    </div>

</s:form>


