<%-- 
    Document   : maklumatBangunanPHPC
    Created on : Jul 14, 2010, 5:28:35 PM
    Author     : faidzal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
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

    function save(event, f){
        

            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div').html(data);

            },'html');



        
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form  name="form1" beanclass="etanah.view.strata.MaklumatBangunanPHPCActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bangunan</legend>
            <p>
                <label>Nama Perbadanan Pengurusan : </label>
                <s:select name="rujukanLuar.agensi.kod" value="rujukanLuar.agensi.kod" id="kod">
                        <s:option>Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodAgensi}" label="nama" value="kod"/>
                    </s:select><em>*</em>
            </p>

            <p>
                <label>Tarikh :</label><s:text name="rujukanLuar.tarikhSidang" id="datepicker" class="datepicker" size="12" /><em>*</em>
            </p>
            <p>
                <label>Nombor Rujukan :</label>
                <s:text name="rujukanLuar.noRujukan" id="noRujukan"/><em>*</em>
            </p>
            <p>
                <label>Keterangan :</label><s:textarea name="rujukanLuar.ulasan" id="ulasan" cols="50" rows="5"/><em>*</em>
            </p>
            <p>
                <br>
                <label>&nbsp;</label>
                <c:if test="${actionBean.rujukanLuar eq null}">
                   
                    <s:button name="simpanPerbadanan" id="simpan" value="simpan" class="btn" onclick="save(this.name, this.form);" ></s:button>
                </c:if>
                <c:if test="${actionBean.rujukanLuar ne null}">
                    <s:button name="simpanPerbadanan" id="simpan" value="Kemaskini" class="btn" onclick="save(this.name, this.form);"/>
                </c:if>
            </p>
        </fieldset>
    </div>

</s:form>
