<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script>
    function savePeneyerah(frm,hakmilikArray) {                  
                        var url = "${pageContext.request.contextPath}/utiliti/validateHakmilik?savePenyerah&hakmilikArray="+hakmilikArray;
                        if(confirm("Adakah anda pasti untuk Simpan?")){
                            frm.action = url;
                            frm.submit();
                        }
     }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        //alert('test');
        maximizeWindow();
    });
</script>

<s:form action="/utiliti/validateHakmilik" id="validateHakmilik" name="validateHakmilik">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Penyerah
            </legend>
            <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel">Nama Penyerah :&nbsp;</td>
                    <td id="tdDisplay">
                        <s:text name="namaPenyerah" id="namaPenyerah" size="50" class="normal_text" value="" />
                    </td>
                    </tr>

                    <tr><td id="tdLabel">Nombor Telefon :&nbsp;</td>
                        <td id="tdDisplay">
                        <s:text name="noPhone" id="noPhone" size="15" class="normal_text" value=""/>
                        </td>
                    </tr>                   
            </table>
             <s:button name="save" value="Simpan" class="btn" onclick="savePenyerah(document.forms.validateHakmilik,'${actionBean.list}');"/>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            <br>
        </fieldset>
    </div>

</s:form>