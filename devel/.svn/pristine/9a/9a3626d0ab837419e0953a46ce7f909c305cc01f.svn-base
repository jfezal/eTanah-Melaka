<%--
    Document   : nota_daftar_betul
    Created on : Dec 31, 2009, 2:06:56 PM
    Author     : wan.fairul
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript">

    $(document).ready( function(){
       
    <%--set focus--%>
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
            $('textarea').focus(function() {
                $(this).addClass("focus");
            });

            $('textarea').blur(function() {
                $(this).removeClass("focus");
            });
        });

     

       
                      



</script>
<style type="text/css">
    input.text{
        width: 300px !important;
    }
</style>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
    <s:messages />
    <s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembetulan</legend>
            <p>
                <label for="noFail">No. Rujukan Fail:</label>
                ${actionBean.permohonanRujukanLuar.noFail}&nbsp;
            </p>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BETHM'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETP'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETC'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETLP'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETEN'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETPB'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETST'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETSW'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETUL'
                          or actionBean.permohonan.kodUrusan.kod eq 'BETUR'}">
                  <p>
                      <label>Sebab Pembetulan:</label>
                      <s:textarea name="permohonanRujukanLuar.catatan" rows="5" cols="60" readonly="true"/>
                  </p>
            </c:if>

            <br/>
        </fieldset>



    </div>
    <br>

</s:form>