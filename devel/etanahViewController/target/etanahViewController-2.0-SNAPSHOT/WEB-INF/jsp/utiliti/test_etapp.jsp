<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        $('#test1').hide();
        $('#test2').hide();
        $('.document').hide();
        $('#addDoc').click(function() {
            if ($(this).is(':checked')) {
                $('.document').show();
                $('.single').hide();
            } else {
                $('.single').show();
                $('.document').hide();
            }
        });
        $('.radio').click( function () {
            var val = $(this).val();
            if (val === '1') {
                $('#test1').show();
                $('#test2').hide();
            } else {
                $('#test2').show();
                $('#test1').hide();
                
            }
        });
    });

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.stripes.TestAction">    
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>

    <div class="subtitle" id="main">
        <fieldset class="aras1">
            <legend>Test sending fail to eTapp</legend> 
            <p>
                <label>&nbsp;</label>
                <s:radio name="radio" value="2" class="radio"/>Hakmilik Federal 
                <s:radio name="radio" value="1" class="radio"/>Test MMK
            </p>
            
            <div id="test2">
                <p>
                    <label>ID Hakmilik</label>
                <s:text name="idHakmilik" size="30"/>
                </p >
                <p>
                    <label>&nbsp;</label>
                <s:submit name="testHakmilikFed" id="search" value="TEST" class="longbtn"/>
                </p>
            </div>
            <div id="test1">
                <p>
                    <label><font color="red">*</font>No Fail :</label>
                <s:text name="noFail" size="30"/>

                </p>
                <p>
                    <label><font color="red">*</font>Keterangan :</label>
                <s:text name="keterangan" size="30"/>

                </p>
                <p>
                    <label><font color="red">*</font>Jenis Borang :</label>
                <s:text name="borang" size="30"/>

                </p>
                <p>
                    <label>Add Documents?</label>
                <s:checkbox id="addDoc" name="checkbox"/>
                </p>
                <p class="single">
                    <label>&nbsp;</label>
                <s:submit name="testEtappConnection" id="search" value="TEST" class="btn"/>
                </p>
                <p class="document">
                    <label>ID Document:</label>
                <s:text name="idDokumen" size="30"/>
                </p >
                <p class="document">
                    <label>&nbsp;</label>
                <s:submit name="testEtapWithAttachment" id="search" value="TEST" class="btn"/>
                </p>
            </div>            
        </fieldset>

    </div>

</s:form>
