<%--
    Document   : maklumat_keputusan_mesyuarat
    Created on : Jan 25, 2013, 11:16:21 AM
    Author     : latifah.iskak
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
   
    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_keputusan_mesyuarat?deleteSingle&idMesyuarat='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }


    function refreshPageMesyuarat(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_keputusan_mesyuarat?refreshPage';
        $.get(url,
        function(data){
            $('#page_div').html(data);
            //refreshPage();
        },'html');
    }



    function addRecord(){
        
        var url = "${pageContext.request.contextPath}/penguatkuasaan/maklumat_keputusan_mesyuarat?addRecord";

        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function editRecord(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_keputusan_mesyuarat?addRecord&idMesyuarat='+id;
        params  = 'width='+screen.width;
        params += ', height='+screen.height;
        params += ', top=0, left=0'
        params += ', fullscreen=no';
        params += ', directories=no';
        params += ', location=no';
        params += ', menubar=no';
        params += ', resizable=no';
        params += ', scrollbars=yes';
        params += ', status=no';
        params += ', toolbar=no';
        newwin=window.open(url,'PopUp', params);
        if (window.focus) {newwin.focus()}
        return false;
    }

    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }


</script>

<s:form beanclass="etanah.view.penguatkuasaan.MaklumatOrangDisyakiActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <c:if test="${edit}">
                    <fieldset class="aras1">
                        <legend>
                            Keputusan Mesyuarat
                        </legend>
                        <div class="instr-fieldset"><br>
                            Klik butang tambah untuk masukkan maklumat keputusan mesyuarat
                        </div>
                        <br>
                        <div class="content" align="center">
                            <display:table name="${actionBean.senaraiMesyuarat}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                                <display:column title="Bil" sortable="true" style="width:3px;">${line_rowNum}</display:column>
                                <display:column title="Tarikh & Masa"><fmt:formatDate pattern="dd/MM/yyyy hh:mm aaa" value="${line.tarikh}"/></display:column>
                                <display:column title="Tempat">${line.tempat}</display:column>
                                <display:column title="Keputusan Mesyuarat">${line.keputusan}</display:column>
                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editRecord('${line.idMesyuarat}')"/>
                                    </div>
                                </display:column>
                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeRecord('${line.idMesyuarat}');"/>
                                    </div>
                                </display:column>
                            </display:table>
                            <br>
                            <table>
                                <tr>
                                    <td align="right">
                                        <s:button class="btn" value="Tambah" name="new" id="new" onclick="addRecord();"/>
                                    </td>
                                </tr>
                            </table>

                        </div>
                    </fieldset>

                </c:if>

                <br>
            </div>
        </fieldset>
    </div>
</s:form>