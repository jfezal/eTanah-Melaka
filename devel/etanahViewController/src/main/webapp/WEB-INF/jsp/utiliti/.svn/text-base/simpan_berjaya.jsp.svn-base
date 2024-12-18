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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
    input , select {
        width: 95%;
    }
    td{
        font-size: 100% !important;
    }
    .arrow { background:transparent url(${pageContext.request.contextPath}/pub/images/arrows.png) no-repeat scroll 0px -16px; width:100%; height:16px; display:block;}
    .up { background-position:0px 0px;}

</style>
<script type="text/javascript">
    $(document).ready(function() {
        $('#simpan').attr('disabled', 'true');
    });


    function validate(val) {
        if ((!$('#sebab').val() == '')) {
            $('#simpan').removeAttr("disabled");
        }
        else {
            $('#simpan').attr('disabled', 'true');
        }
    }


</script>

<s:form beanclass="etanah.view.utility.BatalPerserahanUtilitiTukarGanti" name="form1">
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <div id="page_div">
        <s:messages />
        <s:errors />
        <div class="subtitle">
            <fieldset class="aras1">               
                </br></br></br>
                <div align="center">
                    <table class="tablecloth" width="70%">
                        <font color="green" size="2">Permohonan Berjaya Dibatalkan</font></br></br></br>
                        <div align="center">                                  
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>
                        </div>
                    </table>
                </div>
            </fieldset>
        </div>
    </div>
</s:form>
