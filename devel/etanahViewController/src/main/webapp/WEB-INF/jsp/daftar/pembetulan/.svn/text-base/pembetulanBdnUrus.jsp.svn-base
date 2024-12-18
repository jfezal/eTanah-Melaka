<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/colorbox.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.colorbox.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }

    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}
</style>
<script type="" language="javascript">
    function popup(url)
        {
            params  = 'width='+screen.width;
            params += ', height='+screen.height;
            params += ', top=0, left=0'
            params += ', fullscreen=no';
            params += ', width=910';
            params += ', height=600';
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
</script>
    

<s:form beanclass="etanah.view.stripes.nota.pembetulanSTRActionBean" class="form1">

    <s:messages  />
    <s:errors />
    <div>
        <table class="tablecloth" width="70%" style="margin-left: auto; margin-right: auto;">
            <tr><td>
                    <fieldset class="aras1" >
                        <legend>
                        </legend>
                        <p style="color:red">
                            *Sila pilih ID Hakmilik untuk membuat pembetulan.Kemudian klik(<img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';"/>)<br/>
                        </p>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                                           id="line">
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" />
                                <display:column title="Baiki / Semak">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem' id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup('${pageContext.request.contextPath}/daftar/pembetulan/betulSTR?popupBdnUrus&idH=${line.hakmilik.idHakmilik}');"/>
                                    </div>
                                </display:column>
                            </display:table>
                            <br/>
                            <br/>
                            &nbsp;
                        </div>

                    </fieldset>
                </td></tr>
        </table>
    </div>
</s:form>