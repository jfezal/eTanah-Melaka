<%-- 
    Document   : syarat_tambahanEdit
    Created on : Jul 10, 2013, 2:33:51 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kemaskini Maklumat Borang</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">       
    function refreshpage2(type){
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?refreshpage&type='+type;
        window.open(url, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }
              
    function addBorang(){
    <%--alert(id);--%>
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?showFormPopUp", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function deleteBorang(idTuntutanKos,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?deleteRow&idKandungan='+idTuntutanKos+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    self.refreshpage2('tBorang') ;
                }, 'html');
                
                
            }
        }
        function editBorang(idTuntutanKos)
        {
            NoPrompt();
            window.open("${pageContext.request.contextPath}/pelupusan/kertas_pindaanPTDV2?showFormKemaskiniNilai&idTuntutanKos="+idTuntutanKos, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function refreshpage(){
            NoPrompt();
        opener.refreshV2KertasPindaaanPTDV2('main');
        self.close();
        }

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = false;
        }

    </script>
    <s:form beanclass="etanah.view.stripes.pelupusan.KertasRencanaPindaanPTDV2ActionBean" name="form">
        <s:errors/>
        <s:messages/>
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <div id="maklumatpendeposit">
                    <legend>Maklumat Borang</legend>
                    <div class="content" align="center">
                        <table class="tablecloth" border="0">
                            <tr>
                                <th>Item</th><th>Amaun Bayaran</th><th>Tindakan</th>
                                <c:if test="${!empty actionBean.listPermohonanTuntutanKos}">
                                </tr>       
                                <tr>
                                    <c:forEach items="${actionBean.listPermohonanTuntutanKos}" var="line">
                                        <td>
                                            ${line.item}
                                        </td>
                                        <td>
                                            RM <s:format value="${line.amaunTuntutan}" formatPattern="#,###,##0.00"/>
                                        </td>
                                        <td>
                                            <a onclick="deleteBorang(${line.idKos},this.form,'mohonTuntutKos')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                            <a onclick="editBorang(${line.idKos})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Harga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                                <%--<a onclick="editBank(${line.idPermohonanPihakPendeposit})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini Bank"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/upload.png'/></a>--%>
                                        </td>
                                    </tr>
                                </c:forEach>
                                 <tr>
                                    <td>Jumlah </td>
                                    <td>RM <s:format value="${actionBean.total}" formatPattern="#,###,##0.00"/></td>
                                </tr>   
                            </c:if>
                        </table>
                    </div>
                </div>
            </fieldset>
            <fieldset class="aras1">
                <table  width="100%" border="0">
                    <tr>
                        <td align="center">
                            <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="addBorang()"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                        </td>
                    </tr>
                </table>   
            </fieldset>
        </div>
    </s:form>
</body>