<%-- 
    Document   : kemasukan_luas_pajakan
    Created on : Dec 3, 2009, 3:30:19 PM
    Author     : fikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function(){
        $('.luas').keyup(function(){
            $(this).val(dodacheck($(this).val()));
        });       
    });


    function checkLuas(val, id, id2) {
                $.get("${pageContext.request.contextPath}/daftar/pajakan?checkLuas&idHakmilik=" + id + '&luas='+ val,
                    function(data){
                        if (data == 1) {
                            alert('Jumlah luas dimasukan melebihi luas yang ada.');
                            $('#'+ id2).val('');
                        }
                    });
    }

    function dodacheck(val) {
        var luas = val;
        var temp = luas.split('.');
        var strLength = temp[1].length;
        if(strLength > 4){
            var tst = temp[1].substring(0, (strLength) - 1);
            //$('#luas').val(temp[0]+'.'+tst);
            luas = temp[0]+'.'+tst;
        }
        return checkdot(luas);
    }
    //CHECK DOT IN LUAS AND ALLOW ONLY ONE DOT IN IT
    function checkdot(val){
        var luas = val;
        var temp = luas.split('.');
        var arrLength = temp.length;
        var strLength = temp[1].length;
        if(arrLength > 2){
            var tst = temp[1].substring(0,(strLength));
            luas = temp[0]+'.'+tst;
        }
        return luas;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="kodUom"/>
<s:messages/>
<s:errors/>
<s:form beanclass="etanah.view.daftar.Pajakan">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Luas Pajakan</legend>
            <div align="center" class="content">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="ID Hakmilik" class="idHakmilik${line_rowNum}">${line.hakmilik.idHakmilik}</display:column>
                    <display:column property="hakmilik.noLot" title="No Lot" />
                    <display:column title="Luas">
                        <s:text name="luas[${line_rowNum-1}]" id="luas${line_rowNum-1}" class="luas" formatType="decimal" formatPattern="#,##0.0000"
                                onblur="checkLuas(this.value, '${line.hakmilik.idHakmilik}', this.id)"/>
                    </display:column>
                    <display:column title="Unit Luas">
                        <s:select name="kodOum[${line_rowNum-1}]">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${kodUom.senaraiKodUOM}" label="nama" value="kod"/>
                    </s:select>
                    </display:column>                    
                </display:table>
            </div>
            <p>
                <label>&nbsp;</label>
                <s:button name="saveLuasPajakan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
            </p>
        </fieldset>
    </div>
</s:form>
