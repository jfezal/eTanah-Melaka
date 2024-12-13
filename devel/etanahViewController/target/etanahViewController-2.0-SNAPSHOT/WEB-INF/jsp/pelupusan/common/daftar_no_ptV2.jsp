<%-- 
    Document   : daftar_no_ptV2
    Created on : Jan 15, 2013, 3:14:54 PM
    Author     : Afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<script type="text/javascript">
    function selectCheckBox()
    {
        var e= $('#sizeHakmilikPermohonan').val();
        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<e;cnt++)
        {
            if(e=='1'){
                if(document.frm.checkmate.checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate.value ;
                }

            }
            else {
                if(document.frm.checkmate[cnt].checked) {
                    //                     alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate[cnt].value ;
                }
            }
        }
        if(data.length == 0){
            alert("Sila pilih hakmilik untuk daftar No PT");
            return false ;
        }
        //                    alert(data) ;
        if(confirm("Daftar No PT?")) {
            var url = '${pageContext.request.contextPath}/pelupusan/daftar_no_ptV2?daftarNoPT&item='+data ;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DaftarNoPTActionBeanV2" name="frm">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <s:hidden name="${actionBean.sizeHakmilikPermohonan}" id="sizeHakmilikPermohonan" value="${actionBean.sizeHakmilikPermohonan}"/>
            <display:table class="tablecloth" name="${actionBean.noPTList}" cellpadding="0" cellspacing="0" id="line"
                           requestURI="/pelupusan/daftar_no_ptV2">
                <c:if test="${actionBean.daftarPT eq true}">
                    <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.hakmilikPermohonan.id}" /></display:column>
                </c:if>
                <c:if test="${line.hakmilikPermohonan.hakmilik ne null}">
                    <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" property="hakmilikPermohonan.permohonan.idPermohonan"/>
                    </c:if>    
                    <c:if test="${actionBean.daftarPT eq true}">
                        <display:column title="No. PT" property="noPt"/>
                    </c:if>
                    <c:if test="${actionBean.daftarPT eq false}">
                        <display:column title="No. PT Sementara" property="noPtSementara"/>
                    </c:if>
                    <display:column title="Bandar/Pekan/Mukim" property="hakmilikPermohonan.hakmilik.bandarPekanMukim.nama"/>
                    <display:column title="Daerah" property="hakmilikPermohonan.hakmilik.bandarPekanMukim.daerah.nama"/>
                </c:if>
                <c:if test="${line.hakmilikPermohonan.hakmilik eq null}">
                    <display:column title="No" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.hakmilikPermohonan.id}"/></display:column>
                    <c:if test="${actionBean.kelompok eq true}">
                        <display:column title="ID Permohonan" property="hakmilikPermohonan.permohonan.idPermohonan"/>
                    </c:if>    
                    <c:if test="${actionBean.daftarPT eq true}">
                        <display:column title="No. PT" property="noPt"/>
                    </c:if>
                    <c:if test="${actionBean.daftarPT eq false}">
                        <display:column title="No. PT Sementara" property="noPtSementara"/>
                    </c:if>
                    <display:column title="Bandar/Pekan/Mukim" property="hakmilikPermohonan.bandarPekanMukimBaru.nama"/>
                    <display:column title="Daerah" property="hakmilikPermohonan.bandarPekanMukimBaru.daerah.nama"/>
                </c:if>
            </display:table>
            <br/>
            <c:if test="${actionBean.daftarPT eq true}">

                <s:button name="daftarNoPT" id="save" value="Daftar No PT" class="longbtn" onclick="selectCheckBox();"/>
            </c:if>
            <c:if test="${actionBean.daftarPT eq false}">
                <s:button name="daftarNoPTSementara" id="save" style="font-size: 12px" value="Daftar No PT Sementara" class="longbtn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </c:if>
        </fieldset>
    </div>

</s:form>